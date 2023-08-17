package ru.radmitr;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.radmitr.core.Line;
import ru.radmitr.core.Station;
import ru.radmitr.core.Transfer;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserWikiMosMetroHtml {

    /** Link with URL encoding */
    private static final String WEB_URL_ENCODED = "https://ru.wikipedia.org/wiki/" +
            "%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_%D1%81%D1%82%D0%B0%D0%BD%D1%86%D0%B8%D0%B9_" +
            "%D0%9C%D0%BE%D1%81%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D0%BE%D0%B3%D0%BE_" +
            "%D0%BC%D0%B5%D1%82%D1%80%D0%BE%D0%BF%D0%BE%D0%BB%D0%B8%D1%82%D0%B5%D0%BD%D0%B0";
    private static final String WEB_URL_DECODED = URLDecoder.decode(WEB_URL_ENCODED, StandardCharsets.UTF_8);

    /** Link with UTF-8 encoding */
    private static final String WEB_URL = "https://ru.wikipedia.org/wiki/Список_станций_Московского_метрополитена";

    /** Pattern for extract line id */
    static final Pattern LINE_ID_FROM_LINK = Pattern.compile("/Moskwa_Metro_Line_(.+).svg/");

    /** All html code of the web page */
    private Document doc;

    /** Table of the Moscow Metro stations */
    private Element tableMetro;

    /** Table of the Moscow Monorail stations */
    private Element tableMonorail;

    /** Table of the Moscow Central Circle platforms */
    private Element tableCircle;

    /**
     * Parse tables (2, 3, 4) from wikidepia:
     * 1 - x
     * 2 - Moscow Metro
     * 3 - Moscow Monorail
     * 4 - Moscow Central Circle
     */
    public ParserWikiMosMetroHtml() throws IOException {
        this.doc = Jsoup.connect(WEB_URL).maxBodySize(0).get();

        this.tableMetro = doc.select("table").get(2);
        this.tableMonorail = doc.select("table").get(3);
        this.tableCircle = doc.select("table").get(4);
    }

    /**
     * Parse all stations
     */
    public Map<String, Set<String>> parseStations() {
        Map<String, Set<String>> stations = new TreeMap<>(new LineIdStringComparator());

        // Stations of the "Moscow Metro"
        Elements rowsMetro = tableMetro.select("tr");
        rowsMetro.stream().skip(1).forEach(row -> {
            Station station = parseStation(row);

            String lineId = station.getLineId();
            String stationName = station.getName();

            if (!stations.containsKey(lineId)) {
                stations.put(lineId, new LinkedHashSet<>());
            }
            stations.get(lineId).add(stationName);
        });

        // Stations of the "Moscow Monorail"
        Elements rowsMonorail = tableMonorail.select("tr");
        rowsMonorail.stream().skip(2).forEach(row -> {
            Station station = parseStationOfMonorail(row);

            String lineId = station.getLineId();
            String stationName = station.getName();

            if (!stations.containsKey(lineId)) {
                stations.put(lineId, new LinkedHashSet<>());
            }
            stations.get(lineId).add(stationName);
        });

        // Stations of the "Moscow Central Circle"
        Elements rowsCircle = tableCircle.select("tr");
        rowsCircle.stream().skip(2).forEach(row -> {
            Station station = parseStationOfCircle(rowsCircle.get(0), row);

            String lineId = station.getLineId();
            String stationName = station.getName();

            if (!stations.containsKey(lineId)) {
                stations.put(lineId, new LinkedHashSet<>());
            }
            stations.get(lineId).add(stationName);

        });

        return stations;
    }

    /**
     * Parse all lines
     */
    public Set<Line> parseLines() {
        Set<Line> lines = new TreeSet<>(new LineIdComparator());

        // Lines of the "Moscow Metro'
        Elements rowsMetro = tableMetro.select("tr");
        rowsMetro.stream().skip(1).forEach(row -> {
            Line line = parseLine(row);
            lines.add(line);
        });

        // Lines of the "Moscow Monorail"
        Elements rowsMonorail = tableMonorail.select("tr");
        rowsMonorail.stream().skip(2).forEach(row -> {
            Line line = parseLineOfMonorail(row);
            lines.add(line);
        });

        // Lines of the "Moscow Central Circle"
        Element firstRowCircle = tableCircle.select("tr").get(0);
        Line lineCircle = parseLineOfCircle(firstRowCircle);
        lines.add(lineCircle);

        return lines;
    }

    /**
     * Parse all transfer stations
     */
    public List<Transfer> parseTransferStations() {
        List<Transfer> transfers = new ArrayList<>();

        // Transfers of the "Moscow Metro"
        Elements rowsMetro = tableMetro.select("tr");
        rowsMetro.stream().skip(1).forEach(row -> {
            Station station = parseStation(row);
            List<Station> connectedStations = parseConnectedStations(row);
            if (connectedStations.size() != 0) {
                Transfer transfer = new Transfer(
                        station,
                        new LinkedHashSet<>(connectedStations)
                );
                transfers.add(transfer);
            }
        });

        // Transfers of the "Moscow Monorail"
        Elements rowsMonorail = tableMonorail.select("tr");
        rowsMonorail.stream().skip(1).forEach(row -> {
            Station station = parseStationOfMonorail(row);
            List<Station> connectedStations = parseConnectedStationsOfMonorail(row);
            if (connectedStations.size() != 0) {
                Transfer transfer = new Transfer(
                        station,
                        new LinkedHashSet<>(connectedStations)
                );
                transfers.add(transfer);
            }
        });

        // Transfers of the "Moscow Central Circle"
        Elements rowsCircle = tableCircle.select("tr");
        rowsCircle.stream().skip(2).forEach(row -> {
            Station station = parseStationOfCircle(rowsCircle.get(0), row);
            List<Station> connectedStations = parseConnectedStationsOfCircle(row);
            if (connectedStations.size() != 0) {
                Transfer transfer = new Transfer(
                        station,
                        new LinkedHashSet<>(connectedStations)
                );
                transfers.add(transfer);
            }
        });

        return transfers;
    }

    //------------------------- Private Functions ---------------------------------

    /**
     * Parse a station of the Moscow Metro from a row
     */
    private Station parseStation(Element row) {
        String lineId = row.select("td:nth-child(1) > span:nth-child(1)").text();

        row.select("td:nth-child(2) > small").remove(); // remove small text
        String stationName = row.select("td:nth-child(2)").text();
        
        return new Station(trimLineId(lineId), stationName);
    }

    /**
     * Parse a station of the Moscow Monorail from a row
     */
    private Station parseStationOfMonorail(Element row) {
        return parseStation(row);
    }

    /**
     * Parse a station of the Moscow Central Circle from a row
     */
    private Station parseStationOfCircle(Element firstRow, Element row) {
        String lineId = firstRow.select("th:nth-child(1) > span:nth-child(1)").text();

        row.select("td:nth-child(2) > small").remove(); // remove small text
        String stationName = row.select("td:nth-child(2)").text();

        return new Station(trimLineId(lineId), stationName);
    }

    /**
     * Parse a line of the Moscow Metro from a row
     */
    private Line parseLine(Element row) {
        String id = row.select("td:nth-child(1) > span:nth-child(1)").text();
        String name = row.select("td:nth-child(1) > span:nth-child(2)").attr("title");
        String hexColor = row.select("td:nth-child(1)").attr("style");

        hexColor = extractHexColor(hexColor);
        String color = HtmlColors.getName(hexColor);

        return new Line(trimLineId(id), name, extractHexColor(color));
    }

    /**
     * Parse a line of the Moscow Monorail from a row
     */
    private Line parseLineOfMonorail(Element row) {
        return parseLine(row);
    }

    /**
     * Parse a line of the Moscow Central Circle from a row
     */
    private Line parseLineOfCircle(Element row) {
        String id = row.select("th > span:nth-child(1)").text();
        String name = row.select("th > a").text();
        String hexColor = row.select("th").attr("style");

        hexColor = extractHexColor(hexColor);
        String color = HtmlColors.getName(hexColor);

        return new Line(trimLineId(id), name, color);
    }

    /**
     * Parse connected stations of the Moscow Metro from a row
     */
    private List<Station> parseConnectedStations(Element row) {
        List<Station> list = new ArrayList<>();
        Elements spans = row.select("td:nth-child(4) > span");

        Iterator<Element> iter = spans.iterator();
        while (iter.hasNext()) {
            String lineId = iter.next().text();
            String stationName = iter.next().attr("title");
            list.add(new Station(trimLineId(lineId), extractStationName(stationName)));
        }

        return list;
    }

    /**
     * Parse connected stations of the Moscow Monorail from a row
     */
    private List<Station> parseConnectedStationsOfMonorail(Element row) {
        return parseConnectedStations(row);
    }

    /**
     * Parse connected stations of the Moscow Central Circle from a row
     */
    private List<Station> parseConnectedStationsOfCircle(Element row) {
        List<Station> list = new ArrayList<>();

        // metro
        Elements spans = row.select("td:nth-child(5) > span.nowrap");
        spans.forEach(span -> {
            String stationName = span.select("a:nth-child(2)").text();
            if (stationName.length() != 0) {
                String imgLink = span.select("span > a > img").attr("src");
                list.add(new Station(extractLineId(imgLink), stationName));
            }
        });

        // D2
        spans.forEach(span -> {
            String lineId = span.select("span.sortkey").text();
            if (lineId.length() != 0) {
                String stationName = span.select("a:nth-child(3)").text();
                list.add(new Station(lineId, stationName));
            }
        });

        // D1
        Elements ps = row.select("td:nth-child(5) > p");
        ps.forEach(p -> {
            String lineId = p.select("span.nowrap > span:nth-child(1)").text();
            if (lineId.length() != 0) {
                String stationName = p.select("span.nowrap > span:nth-child(2)").attr("title");
                list.add(new Station(lineId, extractStationName(stationName)));
            }
        });

        return list;
    }

    /**
     * Trim the first zero from the line id
     */
    private static String trimLineId(String id) {
        return id.replaceFirst("0+(\\d+)", "$1");
    }

    /**
     * Extract the line id from the picture link
     */
    private static String extractLineId(String text) {
        Matcher matcher = LINE_ID_FROM_LINK.matcher(text);
        if (matcher.find()) {
            return URLDecoder.decode(matcher.group(1), StandardCharsets.UTF_8);
        } else {
            return "";
        }
    }

    /**
     * Extract the station name from the "transfers" column,
     * leave only the station name which in «...»
     */
    private static String extractStationName(String text) {
        return text.substring(text.indexOf('«') + 1, text.lastIndexOf('»'));
    }

    /**
     * Extract the hex color, cut of "background:"
     */
    private static String extractHexColor(String text) {
        return text.replaceFirst("background:", "");
    }
}

//****************************************************************************
//**************************** Comparators ***********************************
//****************************************************************************

/**
 * Comparator for sorting metro lines
 *
 * Replace "А" to ".1" and "D" to "10" for compare double value.
 * Examples: 8A -> 8.1
 *           11A -> 11.1
 *           D1 -> 101
 *           D2 -> 102
 */
class LineIdComparator implements Comparator<Line> {

    @Override
    public int compare(Line o1, Line o2) {
        double i1 = Double.parseDouble(o1.getId().replaceAll("А", ".1")
                .replaceAll("D", "10"));
        double i2 = Double.parseDouble(o2.getId().replaceAll("А", ".1")
                .replaceAll("D", "10"));
        return Double.compare(i1, i2);
    }
}

/**
 * Comparator for sorting metro lines
 *
 * Replace "А" to ".1" and "D" to "10" for compare double value.
 * Examples: 8A -> 8.1
 *           11A -> 11.1
 *           D1 -> 101
 *           D2 -> 102
 */
class LineIdStringComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        double i1 = Double.parseDouble(o1.replaceAll("А", ".1")
                .replaceAll("D", "10"));
        double i2 = Double.parseDouble(o2.replaceAll("А", ".1")
                .replaceAll("D", "10"));
        return Double.compare(i1, i2);
    }
}

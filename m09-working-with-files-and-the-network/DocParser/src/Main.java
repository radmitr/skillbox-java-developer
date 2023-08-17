import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static final String FILE_PATH = "data/code.html_";
    private static final String WEB_URL = "https://skillbox.ru/courses/";

    public static void main(String[] args) throws Exception {

        //------------------------------------------------------------
        // Get HTML with help of JS:
        // `new XMLSerializer().serializeToString(document)` // JS code
        //------------------------------------------------------------

        // ------------------------------------------------------------
        // Link tag
        // <a href="https://skillbox.ru/course/profession-graphdesigner/" target="_blank" class="ui-product-card-main__wrap"><h3 class="ui-product-card-main__title t t--2">Графический дизайнер</h3></a>
        // class="ui-product-card-main__wrap"
        // ------------------------------------------------------------

        // ------------------------------------------------------------
        // Variant 1
        // ------------------------------------------------------------
        // get HTML string from copied file
        String htmlFile = parseFile(FILE_PATH);

        Document doc = Jsoup.parse(htmlFile);
        Elements elements = doc.select("a.ui-product-card-main__wrap");

        // print link tags
        elements.forEach(System.out::println);

        // print courses
        // 1
//        elements.forEach(element -> System.out.println(element.text()));
        // 2 - with index
        AtomicInteger index = new AtomicInteger();
        elements.stream()
                .map(Element::text)
                .forEach(t -> System.out.println(index.incrementAndGet() + ". " + t));
        // ------------------------------------------------------------

        // ------------------------------------------------------------
        // Variant 2
        // ------------------------------------------------------------
//        // get HTML string from URL
//        String htmlFile = getContentOfHTTPPage(WEB_URL);
//
//        List<Course> courseList = new ArrayList<>();
//
//        Document doc = Jsoup.connect(WEB_URL).get();
//        Elements elements = doc.getElementsByAttributeValue("class", "ui-product-card-main__wrap");
//        elements.forEach(element -> {
////            String url = element.attr("href");
////            String name = element.child(0).text();
////            courseList.add(new Course(url, name));
//            courseList.add(new Course(
//                    element.attr("href"),
//                    element.child(0).text()));
//        });
//
//        courseList.forEach(System.out::println);
        // ------------------------------------------------------------
    }
    
    public static String parseFile(String path) {
        StringBuilder builder = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append(line + "\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private static String getContentOfHTTPPage(String pageAddress) throws Exception {
        StringBuilder sb = new StringBuilder();
        URL pageURL = new URL(pageAddress);
        URLConnection uc = pageURL.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        try {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
        } finally {
            br.close();
        }
        return sb.toString();
    }
}

class Course {
    private String url;
    private String name;

    public Course(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

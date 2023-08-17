package ru.radmitr;

import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Using FileInputStream and FileOutputStream
 */
@Log4j2
public class MainUsingFileStream {

    private final static String WEB_URL = "https://lenta.ru/";
    private final static String PATH_TO_SAVE = "images";

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect(WEB_URL).maxBodySize(0).get();
        Elements elements = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

        File folder = new File(PATH_TO_SAVE);
        if (!folder.exists()) {
            folder.mkdir();
        }

        log.info("Download started");
        System.out.print("Progress -> ");
        elements.forEach(element -> {
            String link = element.attr("abs:src");
            URL url = null;
            try {
                url = new URL(link);
            } catch (MalformedURLException e) {
                log.error(e.getMessage());
            }
            String imageName = link.substring(link.lastIndexOf("/"));
            if (url != null) {
                try (InputStream in = url.openStream();
                     BufferedOutputStream out = new BufferedOutputStream(
                             new FileOutputStream(PATH_TO_SAVE + "/" + imageName))) {
                    in.transferTo(out);
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
            System.out.print("#");
        });
        System.out.println();
        log.info("Download completed");
    }
}

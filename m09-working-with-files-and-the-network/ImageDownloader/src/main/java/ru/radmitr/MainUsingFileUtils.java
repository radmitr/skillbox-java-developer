package ru.radmitr;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Using FileUtils.copyURLToFile()
 */
@Log4j2
public class MainUsingFileUtils {

    private static final String WEB_URL = "https://www.lenta.ru/";
    private final static String PATH_TO_SAVE = "images2";

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect(WEB_URL).maxBodySize(0).get();
        log.info("Connected to " + doc.title());
        Elements elements = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

        elements.forEach(element -> {
            try {
                FileUtils.copyURLToFile(new URL(element.attr("src")),
                        new File(PATH_TO_SAVE + "/" + element.attr("src")
                                .substring(element.attr("src").lastIndexOf("/"))));
                log.info(element.attr("src") + " : " + "saved");
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        });
    }
}

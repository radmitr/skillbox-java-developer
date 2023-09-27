import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

// Task 11.3
public class Main {

    /** The site you are looking for */
    private static String DEFAULT_SITE_URL = "https://lenta.ru/";
//    private static String DEFAULT_SITE_URL = "https://skillbox.ru/";
//    private static String DEFAULT_SITE_URL = "https://www.luxoft.com/";
//    private static String DEFAULT_SITE_URL = "https://www.lanit.ru/";
//    private static String DEFAULT_SITE_URL = "https://tensor.ru/";

    /** File name to save */
    private static final String SITEMAP_FILE = "data/sitemap.txt";

    /** Logger */
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Enter the name of the site (by default: %s):\n", DEFAULT_SITE_URL);
        String url = scanner.nextLine().strip();
        if (url.isEmpty()) {
            url = DEFAULT_SITE_URL;
        }

        System.out.println("Enter the number of threads (0 - set the default value): ");
        int numberOfThreads = scanner.nextInt();

        long startTime = System.currentTimeMillis();

        Node rootNode = new Node(url);
        NodeSiteMapper siteMapper = new NodeSiteMapper(rootNode);

        ///////////////////////////////////////////////////////////////////////
        log.info("Scanning started...");
        if (numberOfThreads == 0) {
            new ForkJoinPool().invoke(siteMapper);
        } else {
            new ForkJoinPool(numberOfThreads).invoke(siteMapper);
        }
        log.info("Scan completed");
        ///////////////////////////////////////////////////////////////////////

        saveSitemapToFile(rootNode, SITEMAP_FILE);

        long duration = (System.currentTimeMillis() - startTime) / 1000;

        log.info("Number of links: {}", NodeSiteMapper.getNumberOfLinks());
        log.info("Number of errors: {}", NodeSiteMapper.getNumberOfErrors());
        log.info("Time of work: {} c", duration);
    }

    //==================================================================================================

    /**
     * Create pretty sitemap with tabs according to level
     */
    private static String createPrettySitemap(Node node) {
        String tabs = String.join("", Collections.nCopies(node.getDepth(), "\t"));
        StringBuilder result = new StringBuilder(tabs + node.getUrl());

        node.getChildren().forEach(child -> {
            result.append("\n").append(createPrettySitemap(child));
        });
        return result.toString();
    }

    /**
     * Create pretty sitemap with tabs according to level (second variant)
     */
    private static String createPrettySitemap2(Node node) {
        String tabs = "\t".repeat(node.getDepth());
        String result = tabs + node.getUrl();

        result += node.getChildren().stream()
                .map(child -> "\n" + createPrettySitemap2(child))
                .collect(Collectors.joining());
        return result;
    }

    /**
     * Save a pretty sitemap to the file
     */
    private static void saveSitemapToFile(Node rootNode, String file) {
        try (FileWriter fw = new FileWriter(SITEMAP_FILE, StandardCharsets.UTF_8);
             BufferedWriter bw = new BufferedWriter(fw))
        {
            String prettySitemap = createPrettySitemap(rootNode);
            bw.write(prettySitemap);
            bw.flush();
            log.info("The data has been successfully written to the file: '{}'",
                    new File(file).getAbsolutePath());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}

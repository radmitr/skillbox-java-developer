import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class NodeSiteMapper extends RecursiveAction {

    /** Connect timeout */
    private static final int CONNECT_TIMEOUT_MS = 20_000;

    /**
     * When requesting pages, you need to pause (using the sleep method() at the stream)
     * so that the site does not block your application's access. Use values from 100 to 150 ms.
     */
    private static final int MIN_CONNECT_DELAY_MS = 150;

    /** URL image or pdf file pattern */
    private static final Pattern URL_IMAGE_OR_PDF_FILE_PATTERN =
            Pattern.compile("(\\S+(\\.(?i)(jpg|png|gif|bmp|pdf))$)");

    /** URL ending by anchor pattern */
    private static final Pattern URL_ENDING_BY_ANCHOR_PATTERN =
            Pattern.compile("#([а-яА-Я\\w-]+)?$");

    /** URL ending by mail pattern */
    private static final Pattern URL_ENDING_BY_MAIL_PATTERN =
            Pattern.compile("\\w+@\\w+\\.\\w+$");

    /** Current node */
    private final Node node;

    /** URL of the current node pattern */
    private Pattern currentUrlPattern = null;

    /** Thread-safe set of all links */
    private static Set<String> allLinks = ConcurrentHashMap.newKeySet();

    /** Number of errors */
    private static AtomicInteger errorCount = new AtomicInteger();

    /** The lock-object for connect delay */
    private static final Object lockDelay = new Object();

    /** Logger */
    private static final Logger log = LogManager.getLogger(NodeSiteMapper.class);

    public NodeSiteMapper(Node node) {
        this.node = node;

        // add the first node (root) to the set
        if (allLinks.size() == 0) {
            allLinks.add(node.getUrl());
            log.info(node.getUrl());
        }
    }

    @Override
    protected void compute() {
        List<NodeSiteMapper> taskList = new ArrayList<>();

        try {
            // make every connect after delay
            synchronized (lockDelay) {
                Thread.sleep(MIN_CONNECT_DELAY_MS);
            }
            Connection connection = Jsoup.connect(node.getUrl())
                    .maxBodySize(0)
                    .timeout(CONNECT_TIMEOUT_MS);
            Document doc = connection.get();
            Elements elements = doc.select("body").select("a");

            for (Element a : elements) {
                String childUrl = a.absUrl("href");
                childUrl = deleteParamsInUrl(childUrl);

                if (isCorrectUrl(childUrl)) {
                    if (!allLinks.contains(childUrl)) {
                        node.addChild(new Node(childUrl));
                        allLinks.add(childUrl);
                        log.info(childUrl);
                    }
                }
            }
        } catch (SocketTimeoutException e) {
            errorCount.incrementAndGet();
            log.error("{}: {} [{}]",e.getClass(), e.getMessage(), node.getUrl());
        } catch (IOException e) {
            // remove relation with parent
            node.getParent().getChildren().removeIf(n -> n.getUrl().equals(node.getUrl()));
            allLinks.removeIf(u -> u.equals(node.getUrl()));
            errorCount.incrementAndGet();
            log.error("{}: {}",e.getClass(), e.getMessage());
        } catch (InterruptedException e) {
            errorCount.incrementAndGet();
            log.error("{}: {}",e.getClass(), e.getMessage());
        }

        for (Node child : node.getChildren()) {
            NodeSiteMapper task = new NodeSiteMapper(child);
            task.fork();
            taskList.add(task);
        }

        for (NodeSiteMapper task : taskList) {
            task.join();
        }
    }

    /**
     * Clear query params in URL if present
     */
    private static String deleteParamsInUrl(String url) {
        return url.replaceAll("\\?.+","");
    }

    /**
     * Check URL
     */
    private boolean isCorrectUrl(String url) {
        if (currentUrlPattern == null) {
            currentUrlPattern = Pattern.compile("^\\Q" + node.getUrl() + "\\E");
        }
        return currentUrlPattern.matcher(url).lookingAt()
                && !URL_IMAGE_OR_PDF_FILE_PATTERN.matcher(url).find()
                && !URL_ENDING_BY_ANCHOR_PATTERN.matcher(url).find()
                && !URL_ENDING_BY_MAIL_PATTERN.matcher(url).find();
    }

    /**
     * Return the number of links
     */
    public static int getNumberOfLinks() {
        return allLinks.size();
    }

    /**
     * Return the number of errors
     */
    public static int getNumberOfErrors() {
        return errorCount.intValue();
    }
}

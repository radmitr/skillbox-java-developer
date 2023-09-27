package affine;

import java.io.File;
import java.util.*;

// Task 11.1 (using affine.ImageResizerAffine)
public class Main {

    /** Source folder */
    private static final String SRC_FOLDER = "src/main/resources/images/src_pic";

    /** Destination folder */
    private static final String DST_FOLDER = "src/main/resources/images/dst_pic_2";

    /** Number available cores */
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws InterruptedException {
        int newWidth = 300;

        resizeImages(SRC_FOLDER, DST_FOLDER, newWidth, 1);
        resizeImages(SRC_FOLDER, DST_FOLDER, newWidth, NUMBER_OF_CORES);
    }

    /**
     * Resize images in threads
     *
     * @param srcDirectory source directory
     * @param dstDirectory destination directory
     * @param newWidth new width for resizing
     * @param numberOfThreads number of threads used for resizing
     */
    public static void resizeImages(String srcDirectory, String dstDirectory, int newWidth, int numberOfThreads)
            throws InterruptedException
    {
        File srcDir = new File(srcDirectory);
        File dstDir = new File(dstDirectory);

        File[] files = srcDir.listFiles();

        System.out.printf("Number of threads: %d\n", numberOfThreads);
        System.out.println("Conversion started. Please wait...");

        if (files != null) {
            // sorting for uniform distribution of files of different sizes across threads
            Arrays.sort(files, Comparator.comparing(File::length));

            numberOfThreads = Math.min(numberOfThreads, NUMBER_OF_CORES);
            List<List<File>> fileLists = groupFiles(files, numberOfThreads);

            List<Thread> threads = new ArrayList<>(fileLists.size());

            for (List<File> fileList : fileLists) {
                ImageResizerAffine resizer = new ImageResizerAffine(fileList, dstDir, newWidth);
                Thread thread = new Thread(resizer);
                threads.add(thread);
            }

            long start = System.currentTimeMillis();

            for (Thread thread : threads) {
                thread.start();
            }
            for (Thread thread : threads) {
                thread.join();
            }

            long duration = System.currentTimeMillis() - start;

            System.out.printf("Total time: %d ms\n\n", duration);
        } else {
            System.out.printf("Directory is empty or not exist: %s\n", SRC_FOLDER);
        }
    }

    /**
     * Sequentially puts one file in each group
     *
     * @param files files for grouping
     * @param numberOfGroups number of groups (file lists)
     * @return list of file lists
     */
    private static List<List<File>> groupFiles(File[] files, int numberOfGroups) {
        List<List<File>> lists = new ArrayList<>();

        for (int i = 0; i < numberOfGroups; i++) {
            lists.add(new ArrayList<>());
        }

        int i = 0;
        for (File file : files) {
            lists.get(i).add(file);
            i++;
            if (i == numberOfGroups) {
                i = 0;
            }
        }
        return lists;
    }
}

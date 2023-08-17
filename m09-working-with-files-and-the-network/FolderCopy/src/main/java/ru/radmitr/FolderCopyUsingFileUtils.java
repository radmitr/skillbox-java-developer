package ru.radmitr;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class FolderCopyUsingFileUtils {

    private static final File SRC_DIR = new File("data/src_folder");
    private static final File DST_DIR = new File("data/dst_folder");

    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            if (!SRC_DIR.isDirectory()) {
                return;
            }
            FileUtils.copyDirectory(SRC_DIR, new File(DST_DIR + "/" + SRC_DIR.getName()));
            long end = System.currentTimeMillis();
            System.out.format("%d ms\n", end - start);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

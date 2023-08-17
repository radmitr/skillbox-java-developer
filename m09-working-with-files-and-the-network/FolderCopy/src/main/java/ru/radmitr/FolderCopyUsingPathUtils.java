package ru.radmitr;

import org.apache.commons.io.file.PathUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FolderCopyUsingPathUtils {

    private static final Path SRC_DIR = Path.of("data/src_folder");
    private static final Path DST_DIR = Path.of("data/dst_folder");

    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            if (!Files.isDirectory(SRC_DIR)) {
                return;
            }
            if (!Files.exists(DST_DIR)) {
                Files.createDirectory(DST_DIR);
            }
            PathUtils.copyDirectory(
                    SRC_DIR, DST_DIR.resolve(SRC_DIR.getFileName()),
                    StandardCopyOption.REPLACE_EXISTING
            );
            long end = System.currentTimeMillis();
            System.out.format("%d ms\n", end - start);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
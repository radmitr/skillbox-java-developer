package ru.radmitr;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class FolderCopyUsingFilesWalk {

    private static Scanner scanner;

    public static void main(String[] args) {
        String separator = File.separator;
        scanner = new Scanner(System.in);

        System.out.println("Enter source directory");
        while (scanner.hasNext()) {
            // Source
            String input1 = scanner.nextLine().trim();
            if (input1.equalsIgnoreCase("EXIT")) {
                break;
            }
            Path dir1 = Path.of(input1);
            if (!dir1.isAbsolute()) {
                dir1 = dir1.toAbsolutePath();
            }

            // Destination
            System.out.println("Enter destination directory");
            String input2 = scanner.nextLine().trim();
            if (input2.equalsIgnoreCase("EXIT")) {
                break;
            }
            Path dir2 = Path.of(input2);
            if (!dir2.isAbsolute()) {
                dir2 = dir2.toAbsolutePath();
            }

            copyDirectoryUsingFilesWalk(dir1, dir2);

            System.out.println("Done!\n");
            System.out.println("Enter source directory");
        }
    }

    public static void copyDirectoryUsingFilesWalk(Path source, Path target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Incorrect parameter(s)");
        }
        try {
            Files.walk(source)
                    .forEach(currentSource -> {
//                        Path currentTarget = Paths.get(target.toString(), currentSource.toString()
//                                .substring(source.getParent().toString().length()));
                        Path currentTarget = Paths.get(target.toString(), source.relativize(currentSource).toString());
                        try {
                            Files.copy(currentSource, currentTarget);
                        } catch (NoSuchFileException e) {
                            if (!Files.exists(currentTarget)) {
                                try {
                                    Files.createDirectories(currentTarget);
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            } else {
                                e.printStackTrace();
                            }
                        } catch (FileAlreadyExistsException e) {
                            System.out.println("File already exists: " + e.getFile());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            // Source directory don't exist and we create it
            if (!Files.exists(source)) {
                try {
                    Files.createDirectories(source);
                    copyDirectoryUsingFilesWalk(source, target);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else {
                e.printStackTrace();
            }
        }
    }
}

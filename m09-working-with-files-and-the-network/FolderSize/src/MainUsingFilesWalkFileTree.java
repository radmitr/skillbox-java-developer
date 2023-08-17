import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Scanner;

import static java.lang.Integer.MAX_VALUE;

/**
 * Using Files.walkFileTree
 */
public class MainUsingFilesWalkFileTree {

    private static final Scanner in = new Scanner(System.in);

    private static long size;

    public static void main(String[] args) throws IOException {
        for (;;) {
            Path path = getPath();
            size = 0;

            Files.walkFileTree(path, new HashSet<>(), MAX_VALUE, new FileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
//                    System.out.println("Visit file: " + file);
                    size += file.toFile().length();
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path path, IOException e) {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path path, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });

            print(size);
        }
    }

    public static Path getPath() {
        Path path;

        for (;;) {
            path = null;
            System.out.println("Введите путь к папке:");
            String input;

            if (in.hasNext()) {
                input = in.next();
            } else {
                break;
            }

            if (input.isEmpty()) {
                continue;
            }

            path = Paths.get(input);
            if (Files.isDirectory(path)) {
                break;
            } else {
                System.out.println(input + " - не путь к папке!");
            }
        }
        return path;
    }

    public static void print(double size) {
        int numDevisions = 0;

        while (size  > 1024) {
            numDevisions++;
            size /= 1024;
        }

        switch (numDevisions) {
            case 0:
                System.out.println(size + "Б");
                break;
            case 1:
                System.out.printf("%.2f %s %n", size, "Кб");
                break;
            case 2:
                System.out.printf("%.2f %s %n", size, "Мб");
                break;
            case 3:
                System.out.printf("%.2f %s %n", size, "Гб");
                break;
            case 4:
                System.out.printf("%.2f %s %n", size, "Тб");
                break;
        }
    }
}

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * FolderSize using Files.walk()
 */
public class MainUsingFilesWalk {

    public static void main(String[] args) throws IOException {
        while (true) {
            try {
                System.out.println("Введите путь к папке:");
                String input = new Scanner(System.in).nextLine();
                if (input == null || input.isEmpty()) {
                    continue;
                }
                Path path = Path.of(input);

                if (!Files.exists(path)) {
                    throw new FileNotFoundException();
                }
                print(getFolderSize(path));

            } catch (NullPointerException npe) {
                System.out.println("В указанной папке нет файлов.");
            } catch (FileNotFoundException sfe) {
                System.out.println("Директория не существует. Указан ошибочный путь.");
            }
        }
    }

    private static long getFolderSize(Path path) throws IOException {
        // 1 - Files.size()
//        long result = Files.walk(path).mapToLong(p -> {
//            try {
//                return Files.size(p);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }).sum();
//        return result;

        // 2 - toFile().length()
        return Files.walk(path).mapToLong(p -> p.toFile().length()).sum();
    }

    private static void print(long size) {
        String[] units = new String[] {"B", "KB", "MB", "GB", "TB"};
        int unitIndex = (int) (Math.log10(size) / 3);
        double unitValue = 1 << (unitIndex * 10);
        String readableSize = new DecimalFormat("#,##0.##")
                .format(size / unitValue) + " " + units[unitIndex];
        System.out.println(readableSize);
    }
}

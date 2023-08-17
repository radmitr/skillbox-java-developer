import java.io.File;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Using File
 */
public class MainUsingFile {

    private static long size;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.println("Input folder directory:");
        String input = scanner.nextLine().trim();

        File folder = new File(input);
        if (folder.isFile()) {
            System.out.println("It is the file, not directory");
            return;
        }
        if (!folder.isDirectory()) {
            System.out.println("No such folder directory");
            return;
        }

        // Set separator format
////        DecimalFormat formatter = new DecimalFormat(",###");
//        DecimalFormat formatter = new DecimalFormat();
//        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
//        symbols.setGroupingSeparator(' ');
//        formatter.setDecimalFormatSymbols(symbols);
    
        // 1
//        System.out.printf("size: %d  bytes", getFolderSize(folder).longValue());
//        System.out.printf("size: %d  bytes", getFolderSize(folder));
        
        // 2
        long value = getFolderSize(folder);
        printFolderSize(value);
    
        // 3
//        System.out.println(String.format(Locale.CANADA_FRENCH, "size: %,d Bytes\n", getFolderSize(folder)));
    }
    
    public static long getFolderSize(File folder) {
        size = getFilesSize(folder);
        return size;
    }
    
    private static long getFilesSize(File folder) {
        File[] files = folder.listFiles();
        
        for (File item : files) {
            if (item.isFile()) {
                size += item.length();
            } else {
                getFilesSize(item);
            }
        }
        return size;
    }
    
    private static void printFolderSize(long size) {
        if (size < 1) {
            return;
        }
        DecimalFormat formatter = new DecimalFormat(",###.##");
        double folderSize = size;
    
        // Bytes
        if (folderSize < 1024) {
            System.out.printf("Folder size: %s Bytes\n", formatter.format(size));
            return;
        }
        // KBytes
        folderSize = folderSize / 1024;
        if (folderSize < 1024) {
            System.out.printf("Folder size: %s kBytes (%s Bytes)\n", formatter.format(folderSize), formatter.format(size));
            return;
        }
        // MBytes
        folderSize = folderSize / 1024;
        if (folderSize < 1024) {
            System.out.printf("Folder size: %s MBytes (%s Bytes)\n", formatter.format(folderSize), formatter.format(size));
            return;
        }
        // GBytes
        folderSize = folderSize / 1024;
        if (folderSize < 1024) {
            System.out.printf("Folder size: %s GBytes (%s Bytes)\n", formatter.format(folderSize), formatter.format(size));
            return;
        }
        // TBytes
        folderSize = folderSize / 1024;
        if (folderSize < 1024) {
            System.out.printf("Folder size: %s TBytes (%s Bytes)\n", formatter.format(folderSize), formatter.format(size));
            return;
        }
        // PBytes
        folderSize = folderSize / 1024;
        if (folderSize < 1024) {
            System.out.printf("Folder size: %s PBytes (%s Bytes)\n", formatter.format(folderSize), formatter.format(size));
        } else {
            System.out.printf("Folder size: %s Bytes\n", formatter.format(size));
        }
    }
}

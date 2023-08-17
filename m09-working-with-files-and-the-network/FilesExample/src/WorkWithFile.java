import java.io.File;

public class WorkWithFile {

    public static void main(String[] args) {
        File file = new File("data/info.txt");
        File file2 = new File("data\\info.txt");
        File file3 = new File("d:/Code/JAVA_PRJ/radmitr/skillbox-java-developer" +
                "/m09-working-with-files-and-the-network/FilesExample/data/info.txt");

        // 1 - length()
        System.out.println(file.length());
        System.out.println(file2.length());
        System.out.println(file3.length());
        System.out.println("------------------------");

        // 2 - lastModified()
        System.out.println(file.lastModified());
        System.out.println("------------------------");

        // 3 - delete()
//        System.out.println(file.delete());

        // 4 - isDirectory()
        System.out.println(new File("data").isDirectory());
        System.out.println("------------------------");

        // 5 - listFiles(), getAbsolutePath()
        File folder = new File("C:\\Users\\Дмитрий\\Desktop");
        File[] files = folder.listFiles();

        for (File f : files) {
            System.out.println(f.getAbsolutePath());
        }
        System.out.println("------------------------");

        // 6 - mkdir(), exists()
        File folder2 = new File("data/temp_folder");
        folder2.mkdir();
        System.out.println(folder2.exists());
        folder2.delete();
        System.out.println(folder2.exists());
    }
}

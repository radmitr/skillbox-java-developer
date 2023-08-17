package ru.radmitr;

import java.nio.file.Path;
import java.nio.file.Paths;

class PathRelativizeExample {

    public static void main(String[] args) {
        // 1
        // create objects of Path
        Path path = Paths.get("D:\\eclipse\\p2\\org\\eclipse");
        Path passedPath = Paths.get("D:\\eclipse\\p2\\org\\eclipse"
                + "\\equinox\\p2\\core\\cache\\binary");

        // print paths
        System.out.println("This Path:" + path);
        System.out.println("Given Path:" + passedPath);

        // call relativize() to create
        // a relative path
        Path relativize = path.relativize(passedPath);

        // print result
        System.out.println("Relative Path: " + relativize);

        System.out.println("-------------------------------------------");

        // 2
        // create objects of Path
        Path path2 = Paths.get("D:\\eclipse\\p2\\org\\eclipse");
        Path passedPath2 = Paths.get("D:\\eclipse");

        // print paths
        System.out.println("This Path2:" + path2);
        System.out.println("Given Path2:" + passedPath2);

        // call relativize() to create
        // a relative path
        Path relativize2 = path2.relativize(passedPath2);

        // print result
        System.out.println("Relative Path2: " + relativize2);
    }
}

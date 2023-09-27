package scalr;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class ImageResizerScalr implements Runnable {

    private List<File> files;
    private File dstFolder;
    private int newSize;
    private Scalr.Method method;

    public ImageResizerScalr(List<File> files, File dstFolder, Scalr.Method method, int newSize) {
        this.files = files;
        this.dstFolder = dstFolder;
        this.method = method;
        this.newSize = newSize;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();

        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                BufferedImage newImage = Scalr.resize(image, method, newSize, Scalr.OP_ANTIALIAS);
                File newFile = new File(dstFolder + "/img" + newImage.getWidth() +
                        "x" + newImage.getHeight() + "-scalr-" + file.getName());
                ImageIO.write(newImage, getFileExtension(newFile), newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.printf("%s finished, duration: %d ms\n", Thread.currentThread().getName(),
                System.currentTimeMillis() - start);
    }

    /**
     * Get file extension
     *
     * @param file file to get extension
     * @return file extension
     */
    private static String getFileExtension(File file) {
        if (file == null) {
            return "";
        }
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
}

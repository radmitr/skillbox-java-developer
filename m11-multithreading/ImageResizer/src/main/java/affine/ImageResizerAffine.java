package affine;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashSet;
import java.util.List;

public class ImageResizerAffine implements Runnable {

    private List<File> files;
    private File dstFolder;
    private int newWidth;

    public ImageResizerAffine(List<File> files, File dstFolder, int newWidth) {
        this.files = files;
        this.dstFolder = dstFolder;
        this.newWidth = newWidth;
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
                BufferedImage newImage = ImageResizerAffine.resize(image, newWidth);
                File newFile = new File(dstFolder + "/img" + newImage.getWidth() +
                        "x" + newImage.getHeight() + "-affine-" + file.getName());
                String formatName = (newImage.getType() == 5) ? "jpg" : "png";
                ImageIO.write(newImage, formatName, newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        long duration = System.currentTimeMillis() - start;
        System.out.printf("%s finished, duration: %d ms\n", Thread.currentThread().getName(), duration);
    }

    /**
     * Resize image
     *
     * @param image image for resize
     * @param newWidth new width for image
     * @return a resized BufferedImage.
     */
    private static BufferedImage resize(BufferedImage image, int newWidth) {
        int width = image.getWidth();

        if (width < newWidth) {
            System.out.printf("Original width %d px is lower %d px\n", width, newWidth);
        } else if (width == newWidth) {
            System.out.printf("Image width is already %d px, no need to convert\n", width);
        } else {
            int scale = 0;
            int tempWidth = width;
            while (tempWidth / newWidth > 1) {
                tempWidth /= 2;
                scale++;
            }

            HashSet<Integer> availableWidths = new HashSet<>();
            for (int i = 1; i < 8; i++) {
                availableWidths.add(newWidth * (int) Math.pow(2, i));
            }

            if (!availableWidths.contains(width)) {
                image = transformByAffine(image, newWidth, scale);
                System.out.printf("Resize - prepare:\t%5s px\t->\t %5s px\n",
                        width, image.getWidth());
            } else {
                System.out.printf("Resize - prepare:\t%5s px in set %s, no need to prepare\n",
                        width, availableWidths);
            }

            for (int i = 0; i < scale; i++) {
                tempWidth = image.getWidth();
                image = transformByAffine(image, newWidth, (scale - 1) - i);
                System.out.printf("Resize - step %s:  \t%5s px\t->\t %5s px\n",
                        i, tempWidth, image.getWidth());
            }
        }
        return image;
    }

    /**
     * Affine transform
     */
    private static BufferedImage transformByAffine(BufferedImage image, int newWidth, int scale) {
        double multiply = newWidth * Math.pow(2, scale) / image.getWidth();
        System.out.printf("Multiply: %s\n", multiply);

        AffineTransformOp op = new AffineTransformOp(
                AffineTransform.getScaleInstance(multiply, multiply),
                AffineTransformOp.TYPE_BILINEAR
        );
        return op.filter(image, null);
    }
}

package ru.radmitr;

import java.awt.Color;
import java.util.*;

/**
 * Java code to get a color name from RGB, HEX, AWT color.
 *
 * @author Dmitriy Radionov
 * @version 1.0.0
 */
public class HtmlColors {

    /** Unmodifiable list of ColorItem objects */
    private static final List<ColorItem> COLOR_LIST = initColorList();

    /** Amount of color names */
    public static final int COLOR_NAME_AMOUNT = 139;

    /**
     * Initialize and get unmodifiable list of ColorItem objects.
     *
     * @return unmodifiable list of ColorItem objects
     */
    private static List<ColorItem> initColorList() {
        List<ColorItem> list = new ArrayList<>();

        list.add(new ColorItem("AliceBlue", 0xF0, 0xF8, 0xFF));
        list.add(new ColorItem("AntiqueWhite", 0xFA, 0xEB, 0xD7));
        list.add(new ColorItem("Aqua", 0x00, 0xFF, 0xFF));
        list.add(new ColorItem("Aquamarine", 0x7F, 0xFF, 0xD4));
        list.add(new ColorItem("Azure", 0xF0, 0xFF, 0xFF));
        list.add(new ColorItem("Beige", 0xF5, 0xF5, 0xDC));
        list.add(new ColorItem("Bisque", 0xFF, 0xE4, 0xC4));
        list.add(new ColorItem("Black", 0x00, 0x00, 0x00));
        list.add(new ColorItem("BlanchedAlmond", 0xFF, 0xEB, 0xCD));
        list.add(new ColorItem("Blue", 0x00, 0x00, 0xFF));
        list.add(new ColorItem("BlueViolet", 0x8A, 0x2B, 0xE2));
        list.add(new ColorItem("Brown", 0xA5, 0x2A, 0x2A));
        list.add(new ColorItem("BurlyWood", 0xDE, 0xB8, 0x87));
        list.add(new ColorItem("CadetBlue", 0x5F, 0x9E, 0xA0));
        list.add(new ColorItem("Chartreuse", 0x7F, 0xFF, 0x00));
        list.add(new ColorItem("Chocolate", 0xD2, 0x69, 0x1E));
        list.add(new ColorItem("Coral", 0xFF, 0x7F, 0x50));
        list.add(new ColorItem("CornflowerBlue", 0x64, 0x95, 0xED));
        list.add(new ColorItem("Cornsilk", 0xFF, 0xF8, 0xDC));
        list.add(new ColorItem("Crimson", 0xDC, 0x14, 0x3C));
        list.add(new ColorItem("Cyan", 0x00, 0xFF, 0xFF));
        list.add(new ColorItem("DarkBlue", 0x00, 0x00, 0x8B));
        list.add(new ColorItem("DarkCyan", 0x00, 0x8B, 0x8B));
        list.add(new ColorItem("DarkGoldenrod", 0xB8, 0x86, 0x0B));
        list.add(new ColorItem("DarkGray", 0xA9, 0xA9, 0xA9));
        list.add(new ColorItem("DarkGreen", 0x00, 0x64, 0x00));
        list.add(new ColorItem("DarkKhaki", 0xBD, 0xB7, 0x6B));
        list.add(new ColorItem("DarkMagenta", 0x8B, 0x00, 0x8B));
        list.add(new ColorItem("DarkOliveGreen", 0x55, 0x6B, 0x2F));
        list.add(new ColorItem("DarkOrange", 0xFF, 0x8C, 0x00));
        list.add(new ColorItem("DarkOrchid", 0x99, 0x32, 0xCC));
        list.add(new ColorItem("DarkRed", 0x8B, 0x00, 0x00));
        list.add(new ColorItem("DarkSalmon", 0xE9, 0x96, 0x7A));
        list.add(new ColorItem("DarkSeaGreen", 0x8F, 0xBC, 0x8F));
        list.add(new ColorItem("DarkSlateBlue", 0x48, 0x3D, 0x8B));
        list.add(new ColorItem("DarkSlateGray", 0x2F, 0x4F, 0x4F));
        list.add(new ColorItem("DarkTurquoise", 0x00, 0xCE, 0xD1));
        list.add(new ColorItem("DarkViolet", 0x94, 0x00, 0xD3));
        list.add(new ColorItem("DeepPink", 0xFF, 0x14, 0x93));
        list.add(new ColorItem("DeepSkyBlue", 0x00, 0xBF, 0xFF));
        list.add(new ColorItem("DimGray", 0x69, 0x69, 0x69));
        list.add(new ColorItem("DodgerBlue", 0x1E, 0x90, 0xFF));
        list.add(new ColorItem("FireBrick", 0xB2, 0x22, 0x22));
        list.add(new ColorItem("FloralWhite", 0xFF, 0xFA, 0xF0));
        list.add(new ColorItem("ForestGreen", 0x22, 0x8B, 0x22));
//        list.add(new ColorItem("Fuchsia", 0xFF, 0x00, 0xFF));
        list.add(new ColorItem("Gainsboro", 0xDC, 0xDC, 0xDC));
        list.add(new ColorItem("GhostWhite", 0xF8, 0xF8, 0xFF));
        list.add(new ColorItem("Gold", 0xFF, 0xD7, 0x00));
        list.add(new ColorItem("Goldenrod", 0xDA, 0xA5, 0x20));
        list.add(new ColorItem("Gray", 0x80, 0x80, 0x80));
        list.add(new ColorItem("Green", 0x00, 0x80, 0x00));
        list.add(new ColorItem("GreenYellow", 0xAD, 0xFF, 0x2F));
        list.add(new ColorItem("HoneyDew", 0xF0, 0xFF, 0xF0));
        list.add(new ColorItem("HotPink", 0xFF, 0x69, 0xB4));
        list.add(new ColorItem("IndianRed", 0xCD, 0x5C, 0x5C));
        list.add(new ColorItem("Indigo", 0x4B, 0x00, 0x82));
        list.add(new ColorItem("Ivory", 0xFF, 0xFF, 0xF0));
        list.add(new ColorItem("Khaki", 0xF0, 0xE6, 0x8C));
        list.add(new ColorItem("Lavender", 0xE6, 0xE6, 0xFA));
        list.add(new ColorItem("LavenderBlush", 0xFF, 0xF0, 0xF5));
        list.add(new ColorItem("LawnGreen", 0x7C, 0xFC, 0x00));
        list.add(new ColorItem("LemonChiffon", 0xFF, 0xFA, 0xCD));
        list.add(new ColorItem("LightBlue", 0xAD, 0xD8, 0xE6));
        list.add(new ColorItem("LightCoral", 0xF0, 0x80, 0x80));
        list.add(new ColorItem("LightCyan", 0xE0, 0xFF, 0xFF));
        list.add(new ColorItem("LightGoldenrodYellow", 0xFA, 0xFA, 0xD2));
        list.add(new ColorItem("LightGray", 0xD3, 0xD3, 0xD3));
        list.add(new ColorItem("LightGreen", 0x90, 0xEE, 0x90));
        list.add(new ColorItem("LightPink", 0xFF, 0xB6, 0xC1));
        list.add(new ColorItem("LightSalmon", 0xFF, 0xA0, 0x7A));
        list.add(new ColorItem("LightSeaGreen", 0x20, 0xB2, 0xAA));
        list.add(new ColorItem("LightSkyBlue", 0x87, 0xCE, 0xFA));
        list.add(new ColorItem("LightSlateGray", 0x77, 0x88, 0x99));
        list.add(new ColorItem("LightSteelBlue", 0xB0, 0xC4, 0xDE));
        list.add(new ColorItem("LightYellow", 0xFF, 0xFF, 0xE0));
        list.add(new ColorItem("Lime", 0x00, 0xFF, 0x00));
        list.add(new ColorItem("LimeGreen", 0x32, 0xCD, 0x32));
        list.add(new ColorItem("Linen", 0xFA, 0xF0, 0xE6));
        list.add(new ColorItem("Magenta(Fuchsia)", 0xFF, 0x00, 0xFF));
        list.add(new ColorItem("Maroon", 0x80, 0x00, 0x00));
        list.add(new ColorItem("MediumAquaMarine", 0x66, 0xCD, 0xAA));
        list.add(new ColorItem("MediumBlue", 0x00, 0x00, 0xCD));
        list.add(new ColorItem("MediumOrchid", 0xBA, 0x55, 0xD3));
        list.add(new ColorItem("MediumPurple", 0x93, 0x70, 0xDB));
        list.add(new ColorItem("MediumSeaGreen", 0x3C, 0xB3, 0x71));
        list.add(new ColorItem("MediumSlateBlue", 0x7B, 0x68, 0xEE));
        list.add(new ColorItem("MediumSpringGreen", 0x00, 0xFA, 0x9A));
        list.add(new ColorItem("MediumTurquoise", 0x48, 0xD1, 0xCC));
        list.add(new ColorItem("MediumVioletRed", 0xC7, 0x15, 0x85));
        list.add(new ColorItem("MidnightBlue", 0x19, 0x19, 0x70));
        list.add(new ColorItem("MintCream", 0xF5, 0xFF, 0xFA));
        list.add(new ColorItem("MistyRose", 0xFF, 0xE4, 0xE1));
        list.add(new ColorItem("Moccasin", 0xFF, 0xE4, 0xB5));
        list.add(new ColorItem("NavajoWhite", 0xFF, 0xDE, 0xAD));
        list.add(new ColorItem("Navy", 0x00, 0x00, 0x80));
        list.add(new ColorItem("OldLace", 0xFD, 0xF5, 0xE6));
        list.add(new ColorItem("Olive", 0x80, 0x80, 0x00));
        list.add(new ColorItem("OliveDrab", 0x6B, 0x8E, 0x23));
        list.add(new ColorItem("Orange", 0xFF, 0xA5, 0x00));
        list.add(new ColorItem("OrangeRed", 0xFF, 0x45, 0x00));
        list.add(new ColorItem("Orchid", 0xDA, 0x70, 0xD6));
        list.add(new ColorItem("PaleGoldenrod", 0xEE, 0xE8, 0xAA));
        list.add(new ColorItem("PaleGreen", 0x98, 0xFB, 0x98));
        list.add(new ColorItem("PaleTurquoise", 0xAF, 0xEE, 0xEE));
        list.add(new ColorItem("PaleVioletRed", 0xDB, 0x70, 0x93));
        list.add(new ColorItem("PapayaWhip", 0xFF, 0xEF, 0xD5));
        list.add(new ColorItem("PeachPuff", 0xFF, 0xDA, 0xB9));
        list.add(new ColorItem("Peru", 0xCD, 0x85, 0x3F));
        list.add(new ColorItem("Pink", 0xFF, 0xC0, 0xCB));
        list.add(new ColorItem("Plum", 0xDD, 0xA0, 0xDD));
        list.add(new ColorItem("PowderBlue", 0xB0, 0xE0, 0xE6));
        list.add(new ColorItem("Purple", 0x80, 0x00, 0x80));
        list.add(new ColorItem("Red", 0xFF, 0x00, 0x00));
        list.add(new ColorItem("RosyBrown", 0xBC, 0x8F, 0x8F));
        list.add(new ColorItem("RoyalBlue", 0x41, 0x69, 0xE1));
        list.add(new ColorItem("SaddleBrown", 0x8B, 0x45, 0x13));
        list.add(new ColorItem("Salmon", 0xFA, 0x80, 0x72));
        list.add(new ColorItem("SandyBrown", 0xF4, 0xA4, 0x60));
        list.add(new ColorItem("SeaGreen", 0x2E, 0x8B, 0x57));
        list.add(new ColorItem("SeaShell", 0xFF, 0xF5, 0xEE));
        list.add(new ColorItem("Sienna", 0xA0, 0x52, 0x2D));
        list.add(new ColorItem("Silver", 0xC0, 0xC0, 0xC0));
        list.add(new ColorItem("SkyBlue", 0x87, 0xCE, 0xEB));
        list.add(new ColorItem("SlateBlue", 0x6A, 0x5A, 0xCD));
        list.add(new ColorItem("SlateGray", 0x70, 0x80, 0x90));
        list.add(new ColorItem("Snow", 0xFF, 0xFA, 0xFA));
        list.add(new ColorItem("SpringGreen", 0x00, 0xFF, 0x7F));
        list.add(new ColorItem("SteelBlue", 0x46, 0x82, 0xB4));
        list.add(new ColorItem("Tan", 0xD2, 0xB4, 0x8C));
        list.add(new ColorItem("Teal", 0x00, 0x80, 0x80));
        list.add(new ColorItem("Thistle", 0xD8, 0xBF, 0xD8));
        list.add(new ColorItem("Tomato", 0xFF, 0x63, 0x47));
        list.add(new ColorItem("Turquoise", 0x40, 0xE0, 0xD0));
        list.add(new ColorItem("Violet", 0xEE, 0x82, 0xEE));
        list.add(new ColorItem("Wheat", 0xF5, 0xDE, 0xB3));
        list.add(new ColorItem("White", 0xFF, 0xFF, 0xFF));
        list.add(new ColorItem("WhiteSmoke", 0xF5, 0xF5, 0xF5));
        list.add(new ColorItem("Yellow", 0xFF, 0xFF, 0x00));
        list.add(new ColorItem("YellowGreen", 0x9A, 0xCD, 0x32));

        return Collections.unmodifiableList(list);
    }

    /**
     * Get the closest color name from list of colors.
     *
     * @param r red color
     * @param g green color
     * @param b blue color
     * @return color name
     */
    public static String getName(int r, int g, int b) {
        ColorItem closestMatch = null;
        int minMse = Integer.MAX_VALUE;
        int mse;
        for (ColorItem c : COLOR_LIST) {
            mse = c.computeMSE(r, g, b); // mean squared error
            if (mse < minMse) {
                minMse = mse;
                closestMatch = c;
            }
        }

        if (closestMatch != null) {
            return closestMatch.getName();
        } else {
            return "Undefined";
        }
    }

    /**
     * Get the closest color name from list of colors.
     * Convert int rgb to RGB, then call getName(int, int, int).
     *
     * @param rgb hexadecimal color as int
     * @return color name
     */
    public static String getName(int rgb) {
        int r = (rgb & 0xFF0000) >> 16;
        int g = (rgb & 0xFF00) >> 8;
        int b = (rgb & 0xFF);
        return getName(r, g, b);
    }

    /**
     * Get the closest color name from list of colors.
     * Convert String hex color to int, then call getName(int).
     *
     * @param hex hexadecimal color as String
     * @return color name
     */
    public static String getName(String hex) {
        int decimal = Integer.decode(hex);
        return getName(decimal);
    }

    /**
     * Get the closest color name from list of colors.
     * Call getName(int, int, int).
     *
     * @param color AWT color
     * @return color name
     */
    public static String getName(Color color) {
        return getName(color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Get the hex string of color from the color name.
     *
     * @param name color name
     * @return hex string of color
     */
    public static String nameToHexString(String name) {
        String ret = "";
        for (ColorItem c : COLOR_LIST) {
            if (c.getName().equals(name)) {
                ret = "0x" + Integer.toHexString(getRGB(new Color(c.getR(), c.getG(), c.getB())));
            }
        }
        return ret;
    }

    /**
     * Get RGB value from AWT color.
     *
     * @param c AWT color
     * @return RGB value color
     */
    public static int getRGB(Color c) {
        return c.getRGB() & 0xFFFFFF;
    }

    //=====================================================================================

    /**
     * Static nested class encapsulate RGB and color name.
     *
     * @author Dmitriy Radionov
     */
    private static class ColorItem {
        /** Variables for RGB */
        int r, g, b;

        /** Name of color */
        String name;

        /**
         * Initialize a newly created ColorItem object
         *
         * @param name color name
         * @param r red color
         * @param g green color
         * @param b blue color
         */
        ColorItem(String name, int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.name = name;
        }

        /**
         * Compute mean squared error.
         *
         * @param pixR current R
         * @param pixG current G
         * @param pixB current B
         * @return mean squared error
         */
        int computeMSE(int pixR, int pixG, int pixB) {
//            return ((pixR - r) * (pixR - r) + (pixG - g) * (pixG - g) + (pixB - b) * (pixB - b)) / 3;
            return (int) (Math.pow(pixR - r, 2) + Math.pow(pixG - g, 2) + Math.pow(pixB - b, 2)) / 3;
        }

        /**
         * Get red color.
         *
         * @return red color
         */
        int getR() {
            return r;
        }

        /**
         * Get green color.
         *
         * @return green color
         */
        int getG() {
            return g;
        }

        /**
         * Get blue color.
         *
         * @return blue color
         */
        int getB() {
            return b;
        }

        /**
         * Get color name.
         *
         * @return color name
         */
        String getName() {
            return name;
        }
    }
}

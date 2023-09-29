package factory.example;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class BorderExample {

    public static void main(String[] args) {
        Border border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        Border border2 = BorderFactory.createBevelBorder(BevelBorder.RAISED);

        CompoundBorder border3 = BorderFactory.createCompoundBorder(border1, border2);

        Border border4 = BorderFactory.createLineBorder(Color.GREEN);

        MatteBorder border5 = BorderFactory.createMatteBorder(0, 10, 50, 30, Color.RED);

        TitledBorder border6 = BorderFactory.createTitledBorder(border3);
    }
}

// Task 5.1.3
public class Main {

    public static void main(String[] args) {
        final int ROW = 7;
        final int COLUMN = 7;
        String[][] arrayX = new String[ROW][COLUMN];

        for (int row = 0; row < ROW; row++) {
            for (int column = 0; column < COLUMN; column++) {
                if (row == column) {
                    arrayX[row][column] = "X";
                } else if (row == (COLUMN - 1 - column)) {
                    arrayX[row][column] = "X";
                } else {
                    arrayX[row][column] = " ";
                }
                System.out.print(arrayX[row][column]);
            }
            System.out.println();
        }
    }
}

// Task 4.3.1
public class Main {

    public static void main(String[] args) {
        System.out.println("Circle Square");
        System.out.println(GeometryCalculator.getCircleSquare(3d));
        System.out.println(GeometryCalculator.getCircleSquare(-5));
        System.out.println(GeometryCalculator.getCircleSquare(6.56));

        System.out.println("\nSphere Volume");
        System.out.println(GeometryCalculator.getSphereVolume(1d));
        System.out.println(GeometryCalculator.getSphereVolume(57.6));
        System.out.println(GeometryCalculator.getSphereVolume(0));

        System.out.println("\nis Triangle Right");
        System.out.println(GeometryCalculator.isTriangleRightAngled(0, 0, 0));
        System.out.println(GeometryCalculator.isTriangleRightAngled(2, 2, 3));
        System.out.println(GeometryCalculator.isTriangleRightAngled(5, 5, 11));

        System.out.println("\nTriangle Square");
        System.out.println(GeometryCalculator.getTriangleSquare(4.0, 10.0, 5.0));
        System.out.println(GeometryCalculator.getTriangleSquare(7.0, 7.0, 8.10));
        System.out.println(GeometryCalculator.getTriangleSquare(4.0, 7.0, 8.10));
    }
}

public class GeometryCalculator {

    public static double getCircleSquare(double radius) {
        return Math.PI * Math.pow(Math.abs(radius), 2);
    }

    public static double getSphereVolume(double radius) {
        return 4 / 3. * Math.PI * Math.pow(Math.abs(radius), 3);
    }

    public static boolean isTriangleRightAngled(double a, double b, double c) {
        return ((Math.abs(a) + Math.abs(b)) > Math.abs(c)) &&
                ((Math.abs(b) + Math.abs(c)) > Math.abs(a)) &&
                ((Math.abs(c) + Math.abs(a)) > Math.abs(b));
    }

    public static double getTriangleSquare(double a, double b, double c) {
        double p = (a + b + c) / 2;
        if (isTriangleRightAngled(a, b, c)) {
            return Math.sqrt(p * (p - a) * (p - b) * (p - c) );
        } else {
            return -1.0;
        }
    }
}

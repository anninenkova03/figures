package figures;

public class Triangle implements Figure {
    private final double a;
    private final double b;
    private final double c;

    public Triangle(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Sides must be positive");
        }
        if (a + b <= c || b + c <= a || c + a <= b) {
            throw new IllegalArgumentException("The given sides do not form a valid triangle");
        }
        if (a + b > Double.MAX_VALUE || b + c > Double.MAX_VALUE || c + a > Double.MAX_VALUE
                || a + b + c > Double.MAX_VALUE) {
            throw new IllegalArgumentException("Perimeter calculation will overflow");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double perimeter() {
        return a + b + c;
    }

    @Override
    public String toString() {
        return String.format("triangle %f %f %f", a, b, c);
    }

    @Override
    public Triangle clone() {
        try {
            return (Triangle) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }
}
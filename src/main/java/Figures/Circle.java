package Figures;

public class Circle implements Figure {
    private final double r;

    public Circle(double r) {
        if (r <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        if (2 * Math.PI * r > Double.MAX_VALUE) {
            throw new IllegalArgumentException("Perimeter calculation will overflow");
        }
        this.r = r;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * r;
    }

    @Override
    public String toString() {
        return String.format("circle %f", r);
    }

    @Override
    public Circle clone() {
        try {
            return (Circle) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }
}
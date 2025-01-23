public class Rectangle implements Figure {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Sides must be positive");
        }
        if (width > Double.MAX_VALUE / 2 || height > Double.MAX_VALUE / 2) {
            throw new IllegalArgumentException("Width or height is too large, causing perimeter overflow");
        }
        if (width + height > Double.MAX_VALUE / 2) {
            throw new IllegalArgumentException("Sum of width and height causes perimeter overflow");
        }
        this.width = width;
        this.height = height;
    }

    @Override
    public double perimeter() {
        return 2 * (width + height);
    }
}


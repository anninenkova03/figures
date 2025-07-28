package factories;

import figures.*;

import java.util.Random;

public class RandomFigureFactory implements FigureFactory {
    private final Random random = new Random();
    private static final double MAX_DIMENSION = 100.0;
    private static final double MIN_DIMENSION = 0.1;

    @Override
    public Figure create() {
        // 0 for Circle, 1 for Rectangle, 2 for Triangle
        int figureType = random.nextInt(3);

        return switch (figureType) {
            case 0 -> createRandomCircle();
            case 1 -> createRandomRectangle();
            case 2 -> createRandomTriangle();
            default -> throw new IllegalStateException("Unexpected random value generated: " + figureType);
        };
    }

    private Circle createRandomCircle() {
        double radius = MIN_DIMENSION + (MAX_DIMENSION - MIN_DIMENSION) * random.nextDouble();
        return new Circle(radius);
    }

    private Rectangle createRandomRectangle() {
        double width = MIN_DIMENSION + (MAX_DIMENSION - MIN_DIMENSION) * random.nextDouble();
        double height = MIN_DIMENSION + (MAX_DIMENSION - MIN_DIMENSION) * random.nextDouble();
        return new Rectangle(width, height);
    }

    private Triangle createRandomTriangle() {
        while (true) {
            try {
                double a = MIN_DIMENSION + (MAX_DIMENSION - MIN_DIMENSION) * random.nextDouble();
                double b = MIN_DIMENSION + (MAX_DIMENSION - MIN_DIMENSION) * random.nextDouble();

                // c must be in range (|a - b|, a + b)
                double lowerBound = Math.max(Math.abs(a - b), MIN_DIMENSION);
                double upperBound = Math.min(a + b, MAX_DIMENSION);
                double c = lowerBound + (upperBound - lowerBound) * random.nextDouble();

                return new Triangle(a, b, c);
            } catch (IllegalArgumentException _) {
            }
        }
    }
}

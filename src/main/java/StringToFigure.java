import Figures.*;

import java.util.Scanner;

public class StringToFigure {
    public Figure createFrom(String representation) {
        if (representation == null || representation.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        representation = representation.replace('.', ',');

        try (Scanner scanner = new Scanner(representation)) {
            String figureType = scanner.next();
            int wordCount = representation.split("\\s+").length;

            switch (figureType.toLowerCase()) {
                case "triangle": {
                    if (wordCount != 4) {
                        throw new IllegalArgumentException("Invalid argument count");
                    }
                    if (!scanner.hasNextDouble()) {
                        throw new IllegalArgumentException("Invalid side a for triangle");
                    }
                    double a = scanner.nextDouble();
                    if (!scanner.hasNextDouble()) {
                        throw new IllegalArgumentException("Invalid side b for triangle");
                    }
                    double b = scanner.nextDouble();
                    if (!scanner.hasNextDouble()) {
                        throw new IllegalArgumentException("Invalid side c for triangle");
                    }
                    double c = scanner.nextDouble();
                    return new Triangle(a, b, c);
                }
                case "circle": {
                    if (wordCount != 2) {
                        throw new IllegalArgumentException("Invalid argument count");
                    }
                    if (!scanner.hasNextDouble()) {
                        throw new IllegalArgumentException("Invalid radius for a circle");
                    }
                    double radius = scanner.nextDouble();
                    return new Circle(radius);
                }
                case "rectangle": {
                    if (wordCount != 3) {
                        throw new IllegalArgumentException("Invalid argument count");
                    }
                    if (!scanner.hasNextDouble()) {
                        throw new IllegalArgumentException("Invalid width for rectangle");
                    }
                    double width = scanner.nextDouble();
                    if (!scanner.hasNextDouble()) {
                        throw new IllegalArgumentException("Invalid height for rectangle");
                    }
                    double height = scanner.nextDouble();
                    return new Rectangle(width, height);
                }
                default:
                    throw new IllegalArgumentException("Unknown figure type: " + figureType);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }
}

package factories;

import java.io.FileInputStream;
import java.io.InputStream;

public class AbstractFigureFactory {

    public static FigureFactory getFactory(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Input type cannot be null");
        }

        switch (type.trim().toLowerCase()) {
            case "random":
                return new RandomFigureFactory();
            case "stdin":
                return new StreamFigureFactory(System.in);
            case "file":
                String path = promptForFilePath();
                try {
                    InputStream fileStream = new FileInputStream(path);
                    return new StreamFigureFactory(fileStream);
                } catch (Exception e) {
                    throw new RuntimeException("Error opening file: " + path, e);
                }
            default:
                throw new IllegalArgumentException("Unknown input type: " + type);
        }
    }

    private static String promptForFilePath() {
        System.out.print("Enter path to input file: ");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        return scanner.nextLine();
    }
}

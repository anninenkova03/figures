import figures.*;

import java.io.InputStream;
import java.util.Scanner;

public class StreamFigureFactory implements FigureFactory {
    private final Scanner scanner;
    private final StringToFigure stringToFigure;

    public StreamFigureFactory(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("Input stream cannot be null");
        }
        this.scanner = new Scanner(inputStream);
        this.stringToFigure = new StringToFigure();
    }

    @Override
    public Figure create() {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.trim().isEmpty()) {
                continue;
            }

            return stringToFigure.createFrom(line);
        }

        return null;
    }
}
package factories;

import figures.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.reflect.ConstructorUtils;

public class StringToFigure {
    public Figure createFrom(String representation) {
        if (representation == null || representation.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        representation = representation.replace(".", ",");

        try (Scanner scanner = new Scanner(representation)) {
            String type = scanner.next();
            String className = Character.toUpperCase(type.charAt(0)) + type.substring(1);
            String fullClassName = "figures." + className;

            Class<?> cls;
            try {
                cls = Class.forName(fullClassName);
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException("Unknown figure type: " + className);
            }
            if (!Figure.class.isAssignableFrom(cls)) {
                throw new IllegalArgumentException(className + " exists, but is not a subtype of Figure.");
            }

            List<Double> args = new ArrayList<>();
            while (scanner.hasNext()) {
                if (scanner.hasNextDouble()) {
                    args.add(scanner.nextDouble());
                } else {
                    throw new IllegalArgumentException("Invalid argument: " + scanner.next());
                }
            }

            Object[] arguments = new Object[args.size()];
            for (int i = 0; i < args.size(); i++) {
                arguments[i] = args.get(i);
            }

            return (Figure) ConstructorUtils.invokeConstructor(cls, arguments);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }
}

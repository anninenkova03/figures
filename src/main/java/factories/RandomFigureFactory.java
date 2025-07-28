package factories;

import figures.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.lang.reflect.Constructor;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.reflections.Reflections;

public class RandomFigureFactory implements FigureFactory {
    private final Random random = new Random();
    private final List<Class<? extends Figure>> figureClasses;

    public RandomFigureFactory() {
        Reflections reflections = new Reflections("figures");
        figureClasses = new ArrayList<>(reflections.getSubTypesOf(Figure.class));
    }

    private static final double MAX_DIMENSION = 100.0;
    private static final double MIN_DIMENSION = 0.1;

    @Override
    public Figure create() {
        while (!figureClasses.isEmpty()) {
            Class<? extends Figure> cls = figureClasses.get(random.nextInt(figureClasses.size()));

            if (cls.getSimpleName().equals("Triangle")) {
                return createRandomTriangle();
            }

            for (Constructor<?> ctor : cls.getConstructors()) {
                Class<?>[] paramTypes = ctor.getParameterTypes();

                if (paramTypes.length > 0 && Arrays.stream(paramTypes).allMatch(p -> p == double.class)) {
                    Object[] args = Arrays.stream(paramTypes)
                            .map(p -> MIN_DIMENSION + random.nextDouble() * (MAX_DIMENSION - MIN_DIMENSION))
                            .toArray();

                    try {
                        return (Figure) ConstructorUtils.invokeConstructor(cls, args);
                    } catch (ReflectiveOperationException _) {}
                }
            }

            figureClasses.remove(cls);
        }

        throw new RuntimeException("No figure types with suitable double-only constructors found.");
    }

    private Triangle createRandomTriangle() {
        while (true) {
            try {
                double a = MIN_DIMENSION + random.nextDouble() * (MAX_DIMENSION - MIN_DIMENSION);
                double b = MIN_DIMENSION + random.nextDouble() * (MAX_DIMENSION - MIN_DIMENSION);
                double lowerBound = Math.max(Math.abs(a - b), MIN_DIMENSION);
                double upperBound = Math.min(a + b, MAX_DIMENSION);
                if (lowerBound >= upperBound) continue;
                double c = lowerBound + random.nextDouble() * (upperBound - lowerBound);
                return new Triangle(a, b, c);
            } catch (IllegalArgumentException _) {}
        }
    }

}
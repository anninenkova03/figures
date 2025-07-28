import figures.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringToFigureTests {

    private final StringToFigure factory = new StringToFigure();

    @Test
    public void testCreateFromEmptyString() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> factory.createFrom(""));
        assertEquals("Input string cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testUnknownFigureName() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> factory.createFrom("unknown 10 20"));
        assertEquals("Unknown figure type: unknown", exception.getMessage());
    }

    @Test
    public void testCreateTriangle() {
        Figure figure = factory.createFrom("triangle 3 4 5");
        assertTrue(figure instanceof Triangle);
        assertEquals(String.format("triangle %f %f %f", 3.0, 4.0, 5.0), figure.toString());
    }

    @Test
    public void testMissingTriangleParameters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createFrom("triangle 10 20");
        });
        assertEquals("Invalid argument count", exception.getMessage());
    }

    @Test
    public void testInvalidTriangleSideA() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createFrom("triangle abc 20 30");
        });
        assertEquals("Invalid side a for triangle", exception.getMessage());
    }

    @Test
    public void testInvalidTriangleSideB() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createFrom("triangle 10 xyz 30");
        });
        assertEquals("Invalid side b for triangle", exception.getMessage());
    }

    @Test
    public void testInvalidTriangleSideC() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createFrom("triangle 10 20 xyz");
        });
        assertEquals("Invalid side c for triangle", exception.getMessage());
    }

    @Test
    public void testTooManyArgumentsForTriangle() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createFrom("triangle 10 20 30 40");
        });
        assertEquals("Invalid argument count", exception.getMessage());
    }

    @Test
    public void testCreateCircle() {
        Figure figure = factory.createFrom("circle 10,5");
        assertTrue(figure instanceof Circle);
        assertEquals(String.format("circle %f", 10.5), figure.toString());
    }

    @Test
    public void testMissingCircleRadius() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createFrom("circle");
        });
        assertEquals("Invalid argument count", exception.getMessage());
    }

    @Test
    public void testInvalidCircleRadius() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createFrom("circle abc");
        });
        assertEquals("Invalid radius for a circle", exception.getMessage());
    }

    @Test
    public void testTooManyArgumentsForCircle() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createFrom("circle 10 15");
        });
        assertEquals("Invalid argument count", exception.getMessage());
    }

    @Test
    public void testCreateRectangle() {
        Figure figure = factory.createFrom("rectangle 4,2 5");
        assertTrue(figure instanceof Rectangle);
        assertEquals(String.format("rectangle %f %f", 4.2, 5.0), figure.toString());
    }

    @Test
    public void testMissingRectangleParameters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createFrom("rectangle 10");
        });
        assertEquals("Invalid argument count", exception.getMessage());
    }

    @Test
    public void testInvalidRectangleWidth() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createFrom("rectangle abc 20");
        });
        assertEquals("Invalid width for rectangle", exception.getMessage());
    }

    @Test
    public void testInvalidRectangleHeight() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createFrom("rectangle 10 xyz");
        });
        assertEquals("Invalid height for rectangle", exception.getMessage());
    }

    @Test
    public void testTooManyArgumentsForRectangle() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createFrom("rectangle 10 20 extra");
        });
        assertEquals("Invalid argument count", exception.getMessage());
    }

    //to check if it catches exceptions from the constructor, tested thoroughly in FigureTests
    @Test
    public void testNegativeArguments() {
        assertThrows(IllegalArgumentException.class,
                () -> factory.createFrom("rectangle -4 5"));
    }

}

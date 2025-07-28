import factories.StringToFigure;
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
        assertEquals("Unknown figure type: Unknown", exception.getMessage());
    }

    @Test
    public void testWrongParameterCount() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createFrom("triangle 10 20");
        });
        assertEquals("No such accessible constructor on object: figures.Triangle", exception.getMessage());
    }

    @Test
    public void testCreateTriangle() {
        Figure figure = factory.createFrom("triangle 3 4 5");
        assertTrue(figure instanceof Triangle);
        assertEquals(String.format("triangle %f %f %f", 3.0, 4.0, 5.0), figure.toString());
    }

    @Test
    public void testCreateCircle() {
        Figure figure = factory.createFrom("circle 10,5");
        assertTrue(figure instanceof Circle);
        assertEquals(String.format("circle %f", 10.5), figure.toString());
    }

    @Test
    public void testCreateRectangle() {
        Figure figure = factory.createFrom("rectangle 4,2 5");
        assertTrue(figure instanceof Rectangle);
        assertEquals(String.format("rectangle %f %f", 4.2, 5.0), figure.toString());
    }

    @Test
    public void testInvalidArgument() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createFrom("triangle 20 abc 30");
        });
        assertEquals("Invalid argument: abc", exception.getMessage());
    }

    //to check if it catches exceptions from the constructor, tested thoroughly in FigureTests
    @Test
    public void testNegativeArguments() {
        assertThrows(IllegalArgumentException.class,
                () -> factory.createFrom("rectangle -4 5"));
    }
}

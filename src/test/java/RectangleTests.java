import figures.Rectangle;

import org.junit.Test;
import static org.junit.Assert.*;

public class RectangleTests {

    @Test
    public void testRectangleProperties() {
        Rectangle rectangle = new Rectangle(2.42, 3.36);
        assertEquals(String.format("rectangle %f %f", 2.42, 3.36), rectangle.toString());
    }

    @Test
    public void testRectanglePerimeter() {
        Rectangle rectangle = new Rectangle(2, 3.1);
        assertEquals(10.2, rectangle.perimeter(), 0);
    }

    @Test
    public void testRectangleInvalidWidth() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> new Rectangle(-3, 4));
        assertEquals("Sides must be positive", exception.getMessage());
    }

    @Test
    public void testRectangleInvalidHeight() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Rectangle(3, 0));
        assertEquals("Sides must be positive", exception.getMessage());
    }

    @Test
    public void testRectangleHeightCausesOverflow() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> new Rectangle(1, Double.MAX_VALUE));

        assertEquals("Width or height is too large, causing perimeter overflow", exception.getMessage());
    }

    @Test
    public void testRectangleSumCausesOverflow() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> new Rectangle(Double.MAX_VALUE / 4, Double.MAX_VALUE / 3));
        assertEquals("Sum of width and height causes perimeter overflow", exception.getMessage());
    }

    @Test
    public void testEdgeCaseValidRectangle() {
        Rectangle rectangle = new Rectangle(Double.MAX_VALUE / 4, Double.MAX_VALUE / 4);
        assertEquals(Double.MAX_VALUE / 2, rectangle.perimeter() / 2, 0.0001);
    }
}
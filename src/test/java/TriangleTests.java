import figures.Triangle;

import org.junit.Test;
import static org.junit.Assert.*;

public class TriangleTests {

    @Test
    public void testTriangleProperties() {
        Triangle triangle = new Triangle(3, 4.42, 5.55);
        assertEquals(String.format("triangle %f %f %f", 3.0, 4.42, 5.55), triangle.toString());
    }

    @Test
    public void testTrianglePerimeter() {
        Triangle triangle = new Triangle(3, 4, 5);
        assertEquals(12, triangle.perimeter(), 0);
    }

    @Test
    public void testTriangleInvalidSide() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> new Triangle(-3, 4, 5));
        assertEquals("Sides must be positive", exception.getMessage());
    }

    @Test
    public void testInvalidTriangleInequalityA() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> new Triangle(4, 1, 2));
        assertEquals("The given sides do not form a valid triangle", exception.getMessage());
    }

    @Test
    public void testInvalidTriangleInequalityB() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> new Triangle(1, 4, 2));
        assertEquals("The given sides do not form a valid triangle", exception.getMessage());
    }

    @Test
    public void testInvalidTriangleInequalityC() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Triangle(1, 2, 4));
        assertEquals("The given sides do not form a valid triangle", exception.getMessage());
    }

    @Test
    public void testTriangleSumCausesOverflow() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Triangle(Double.MAX_VALUE / 3, Double.MAX_VALUE / 3, Double.MAX_VALUE / 3));
        assertEquals("Perimeter calculation will overflow", exception.getMessage());
    }

    @Test
    public void testEdgeCaseValidTriangle() {
        Triangle triangle = new Triangle(Double.MAX_VALUE / 4, Double.MAX_VALUE / 4, Double.MAX_VALUE / 4);
        assertEquals(Double.MAX_VALUE / 4 * 3, triangle.perimeter(), 0.0001);
    }
}
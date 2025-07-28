import figures.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class FigureTests {

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
    public void testSumCausesOverflow() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Triangle(Double.MAX_VALUE / 3, Double.MAX_VALUE / 3, Double.MAX_VALUE / 3));
        assertEquals("Perimeter calculation will overflow", exception.getMessage());
    }

    @Test
    public void testEdgeCaseValidTriangle() {
        Triangle triangle = new Triangle(Double.MAX_VALUE / 4, Double.MAX_VALUE / 4, Double.MAX_VALUE / 4);
        assertEquals(Double.MAX_VALUE / 4 * 3, triangle.perimeter(), 0.0001);
    }

    @Test
    public void testCircleProperties() {
        Circle circle = new Circle(2.36);
        assertEquals(String.format("circle %f", 2.36), circle.toString());
    }

    @Test
    public void testCirclePerimeter() {
        Circle circle = new Circle(2);
        assertEquals(4 * Math.PI, circle.perimeter(), 0);
    }

    @Test
    public void testCircleInvalidRadius() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> new Circle(-5.0));
        assertEquals("Radius must be positive", exception.getMessage());
    }

    @Test
    public void testCircleRadiusCausesOverflow() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> new Circle(Double.MAX_VALUE));
        assertEquals("Perimeter calculation will overflow", exception.getMessage());
    }

    @Test
    public void testEdgeCaseValidCircle() {
        Circle circle = new Circle(Double.MAX_VALUE / (2 * Math.PI));
        assertEquals(Double.MAX_VALUE, circle.perimeter(), 0.0001);
    }

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
import figures.Circle;

import org.junit.Test;
import static org.junit.Assert.*;

public class CircleTests {

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
}
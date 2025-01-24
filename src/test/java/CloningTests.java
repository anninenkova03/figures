import Figures.Circle;
import Figures.Figure;
import Figures.Rectangle;
import Figures.Triangle;
import org.junit.Test;
import static org.junit.Assert.*;

public class CloningTests {

    @Test
    public void testTriangleCloning() {
        Figure triangle = new Triangle(3.0, 4.0, 5.0);
        Figure clonedTriangle = triangle.clone();
        assertEquals(triangle.toString(), clonedTriangle.toString());
    }

    @Test
    public void testCircleCloning() {
        Figure circle = new Circle(3.0);
        Figure clonedCircle = circle.clone();
        assertEquals(circle.toString(), clonedCircle.toString());
    }

    @Test
    public void testRectangleCloning() {
        Figure rectangle = new Rectangle(4.0, 5.0);
        Figure clonedRectangle = rectangle.clone();
        assertEquals(rectangle.toString(), clonedRectangle.toString());
    }

}


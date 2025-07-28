import factories.StreamFigureFactory;
import figures.*;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class StreamFigureFactoryTests {

    private StreamFigureFactory createFactoryFromString(String input) {
        InputStream stream = new ByteArrayInputStream(input.getBytes());
        return new StreamFigureFactory(stream);
    }

    @Test
    public void testCreateTriangle() {
        String input = "triangle 3.0 4.0 5.0\n";
        StreamFigureFactory factory = createFactoryFromString(input);
        Figure figure = factory.create();
        assertTrue(figure instanceof Triangle);
        assertEquals(String.format("triangle %f %f %f", 3.0, 4.0, 5.0), figure.toString());
    }

    @Test
    public void testCreateRectangle() {
        String input = "rectangle 3.0 4.0\n";
        StreamFigureFactory factory = createFactoryFromString(input);
        Figure figure = factory.create();
        assertTrue(figure instanceof Rectangle);
        assertEquals(String.format("rectangle %f %f", 3.0, 4.0), figure.toString());
    }

    @Test
    public void testCreateCircle() {
        String input = "circle 3.0\n";
        StreamFigureFactory factory = createFactoryFromString(input);
        Figure figure = factory.create();
        assertTrue(figure instanceof Circle);
        assertEquals(String.format("circle %f", 3.0), figure.toString());
    }

    @Test
    public void testCreateMultipleFigures() {
        String input = "rectangle 2.0 3.0\ntriangle 3.0 4.0 5.0\ncircle 1.0\n";
        StreamFigureFactory factory = createFactoryFromString(input);

        Figure f1 = factory.create();
        assertTrue(f1 instanceof Rectangle);
        assertEquals(String.format("rectangle %f %f", 2.0, 3.0), f1.toString());

        Figure f2 = factory.create();
        assertTrue(f2 instanceof Triangle);
        assertEquals(String.format("triangle %f %f %f", 3.0, 4.0, 5.0), f2.toString());

        Figure f3 = factory.create();
        assertTrue(f3 instanceof Circle);
        assertEquals(String.format("circle %f", 1.0), f3.toString());

        assertNull(factory.create());
    }

    @Test
    public void testEmptyStream() {
        StreamFigureFactory factory = createFactoryFromString("");
        assertNull(factory.create());
    }

    @Test
    public void testInvalidFigureName() {
        String input = "unknown 1.0\n";
        StreamFigureFactory factory = createFactoryFromString(input);
        assertThrows(IllegalArgumentException.class, factory::create);
    }

    @Test
    public void testInvalidData() {
        String input = "triangle 2.0 2.0\n"; // missing one side
        StreamFigureFactory factory = createFactoryFromString(input);
        assertThrows(IllegalArgumentException.class, factory::create);
    }

    @Test
    public void testContinueAfterError() {
        String input = "triangle 1.0 2.0\nrectangle 2.0 3.0\n"; // first invalid, second valid
        StreamFigureFactory factory = createFactoryFromString(input);

        assertThrows(IllegalArgumentException.class, factory::create);

        Figure figure = factory.create();
        assertTrue(figure instanceof Rectangle);
        assertEquals(String.format("rectangle %f %f", 2.0, 3.0), figure.toString());
    }

}

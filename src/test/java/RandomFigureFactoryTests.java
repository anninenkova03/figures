import factories.RandomFigureFactory;
import figures.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class RandomFigureFactoryTests {

    private static final int SAMPLE_SIZE = 10_000;
    private static final double MIN = 0.1;
    private static final double MAX = 100.0;

    @Test
    public void testRandomFigureDistributionAndValidity() {
        RandomFigureFactory factory = new RandomFigureFactory();

        int circleCount = 0;
        int rectangleCount = 0;
        int triangleCount = 0;

        for(int i = 0; i < SAMPLE_SIZE; i++){
            Figure figure = factory.create();
            assertNotNull(figure);

            if (figure instanceof Circle c){
                circleCount++;
                checkInRange(getRadius(c));
            } else if (figure instanceof Rectangle r){
                rectangleCount++;
                checkInRange(getSides(r)[0]);
                checkInRange(getSides(r)[1]);
            } else if (figure instanceof Triangle t ){
                triangleCount++;
                checkInRange(getSides(t)[0]);
                checkInRange(getSides(t)[1]);
                checkInRange(getSides(t)[2]);
            } else {
                fail("Invalid figure" + figure.getClass().getSimpleName());
            }
        }

        double expected = SAMPLE_SIZE / 3.0;
        assertTrue("Circle distribution outside expected range", circleCount > expected * 0.8 && circleCount < expected * 1.2);
        assertTrue("Rectangle distribution outside expected range", rectangleCount > expected * 0.8 && rectangleCount < expected * 1.2);
        assertTrue("Triangle distribution outside expected range", triangleCount > expected * 0.8 && triangleCount < expected * 1.2);
    }

    private void checkInRange(double value) {
        assertTrue(value >= RandomFigureFactoryTests.MIN && value <= RandomFigureFactoryTests.MAX);
    }

    private double getRadius(Circle c) {
        return Double.parseDouble(c.toString().replace(",", ".").split(" ")[1]);
    }

    private double[] getSides(Rectangle r) {
        String[] parts = r.toString().replace(",", ".").split(" ");
        return new double[] {
                Double.parseDouble(parts[1]),
                Double.parseDouble(parts[2])
        };
    }

    private double[] getSides(Triangle t) {
        String[] parts = t.toString().replace(",", ".").split(" ");
        return new double[] {
                Double.parseDouble(parts[1]),
                Double.parseDouble(parts[2]),
                Double.parseDouble(parts[3])
        };
    }
}

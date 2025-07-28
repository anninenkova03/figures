package figures;

public interface Figure extends Cloneable {
    double perimeter();
    String toString();
    Figure clone();
}

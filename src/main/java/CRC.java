import polynomial.Polynomial;

/**
 * Created by nickolay on 15.11.15.
 */
public class CRC {
    private boolean[] polynomialVector;

    public CRC(String polynomial) {
        this.polynomialVector = new Polynomial(polynomial).getVector();
    }

    public boolean[] encode(boolean[] message) {
        return null;
    }
}

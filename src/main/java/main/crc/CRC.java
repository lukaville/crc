package main.crc;

import main.channel.Message;
import main.polynomial.Polynomial;

/**
 * Created by nickolay on 15.11.15.
 */
public class CRC {
    private final boolean[] polynomialVector;
    private final int polynomialPower;

    public CRC(String polynomialString) {
        Polynomial polynomial = new Polynomial(polynomialString);
        this.polynomialVector = polynomial.getVector();
        this.polynomialPower = polynomial.getPower();
    }

    public int getPower() {
        return polynomialPower;
    }

    public Message encode(Message message) {
        boolean[] encoded = new boolean[message.length() + getPower()];
        return new Message(encoded);
    }

    public Message decode(Message message) {
        boolean[] decoded = new boolean[message.length() - getPower()];
        return new Message(decoded);
    }
}

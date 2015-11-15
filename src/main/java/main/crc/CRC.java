package main.crc;

import main.BitArray;
import main.channel.Message;
import main.polynomial.Polynomial;

import java.util.HashMap;
import java.util.Map;

/**
 * Кодирует сообщение в виде [сообщение][CRC],
 * позволяет декодировать сообщение и исправить
 * ошибку
 */
public class CRC {
    private final boolean[] polynomialVector;
    private final int polynomialPower;

    // Соответствие: вектор синдрома - индекс
    // позиции, где совершена ошибка
    private Map<String, Integer> errorPositions;

    public CRC(String polynomialString) {
        Polynomial polynomial = new Polynomial(polynomialString);
        this.polynomialVector = polynomial.getVector();
        this.polynomialPower = polynomial.getPower();
        this.errorPositions = new HashMap<>();

        // Значения найдены экспериментальным путем
        errorPositions.put("1001", 0);
        errorPositions.put("1101", 1);
        errorPositions.put("1111", 2);
        errorPositions.put("1110", 3);
        errorPositions.put("0111", 4);
        errorPositions.put("1010", 5);
        errorPositions.put("0101", 6);
        errorPositions.put("1011", 7);
        errorPositions.put("1100", 8);
        errorPositions.put("0110", 9);
        errorPositions.put("0011", 10);
    }

    public int getPower() {
        return polynomialPower;
    }

    public Message encode(Message message) {
        boolean[] encoded = new boolean[message.length() + getPower()];

        for (int i = 0; i < message.length(); i++) {
            encoded[i] ^= message.getVector()[i];

            if (encoded[i]) {
                for (int j = 0; j < getPower() + 1; j++) {
                    encoded[i + j] ^= polynomialVector[j];
                }
            }
        }

        System.arraycopy(message.getVector(), 0, encoded, 0, message.length());

        return new Message(encoded);
    }

    public Message decode(Message message) {
        int originalMessageLength = message.length() - getPower();

        boolean[] residue = new boolean[message.length()];
        System.arraycopy(message.getVector(), 0, residue, 0, message.length());

        for (int i = 0; i < originalMessageLength; i++) {
            if (residue[i]) {
                for (int j = 0; j < getPower() + 1; j++) {
                    residue[i + j] ^= polynomialVector[j];
                }
            }
        }

        boolean[] syndrome = new boolean[getPower()];
        System.arraycopy(residue, originalMessageLength, syndrome, 0, getPower());

        // Исправляем ошибку
        boolean[] decoded = new boolean[originalMessageLength];
        System.arraycopy(message.getVector(), 0, decoded, 0, originalMessageLength);
        Integer errorPosition = errorPositions.get(new BitArray(syndrome).toString());
        if (errorPosition != null) {
            decoded[errorPosition] = !decoded[errorPosition];
        }

        return new Message(decoded);
    }
}

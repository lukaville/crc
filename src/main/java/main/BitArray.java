package main;

import java.util.Arrays;

/**
 * Сообщение
 */
public class BitArray {
    private boolean[] vector;

    public BitArray(boolean[] vector) {
        this.vector = vector;
    }

    public BitArray(String str) {
        this.vector = new boolean[str.length()];
        for(int i = 0; i < str.length(); ++i) {
            vector[i] = str.charAt(i) == '1';
        }
    }

    public boolean[] getVector() {
        return vector;
    }

    public int length() {
        return vector.length;
    }

    @Override
    public boolean equals(Object obj) {
        return Arrays.equals(((BitArray) obj).getVector(), getVector());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(boolean b : getVector()) {
            sb.append(b ? "1" : "0");
        }
        return sb.toString();
    }
}

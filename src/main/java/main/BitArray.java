package main;

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
}

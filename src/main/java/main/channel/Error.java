package main.channel;

import main.BitArray;

/**
 * Вектор ошибок
 */
public class Error extends BitArray {
    public Error(String str) {
        super(str);
    }

    public Error(boolean[] vector) {
        super(vector);
    }
}

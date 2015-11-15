package main.channel;

import java.util.List;
import java.util.ArrayList;

/**
 * Генерирует все возможные ошибки с заданной кратностью
 * (кратность - количество единиц в векторе ошибок)
 */
public class ErrorGenerator {
    public static List<Error> generate(final int length, final int multiplicity) {
        List<Error> errors = new ArrayList<Error>();

        for(int i = 0; i < Math.pow(2, length); ++i) {
            if (getTrueCount(i) == multiplicity) {
                boolean[] vector = toBits(i, length);
                errors.add(new Error(vector));
            }
        }

        return errors;
    }

    private static boolean[] toBits(int number, int length) {
        boolean[] bits = new boolean[length];
        for (int i = length - 1; i >= 0; i--) {
            bits[i] = (number & (1 << i)) != 0;
        }
        return bits;
    }

    private static int getTrueCount(int number) {
        int count = 0;
        for (; number > 0; number >>= 1) {
            if ((number & 1) == 1) {
                count++;
            }
        }
        return count;
    }
}

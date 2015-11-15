package main.polynomial;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс хранить полином и позволяет получить
 * вектор степеней полинома
 */
public class Polynomial {
    private Map<Integer, Term> terms;
    private int power;

    public Polynomial(String polynomial) {
        String[] arr = polynomial.replace("-", "+-").split("\\+");

        terms = new HashMap<Integer, Term>();
        for(String termString : arr) {
            Term term = new Term(termString.trim());
            terms.put(term.getPower(), term);
        }

        power = Collections.max(terms.keySet());
    }

    public int getPower() {
        return power;
    }

    public boolean[] getVector() {
        boolean[] vector = new boolean[getPower() + 1];
        for (int i = getPower(); i > -1; i--) {
            vector[getPower() - i] = terms.containsKey(i);
        }
        return vector;
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

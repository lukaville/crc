package main.polynomial;

/**
 * Один член полинома
 */
public class Term implements Comparable<Term> {
    public static final String VARIABLE = "x";
    private int coefficient;
    private int power;

    public Term(int coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
    }

    public Term(String term) {
        String[] parts = term.split(VARIABLE);

        if (parts.length == 0) {
            coefficient = 1;
            power = 1;
        } else if (parts.length == 1) {
            coefficient = Integer.valueOf(parts[0]);
            power = 0;
        } else {
            coefficient = parts[0].length() > 0 ? Integer.valueOf(parts[0]) : 1;
            power = parts[1].length() > 0 ? Integer.valueOf(parts[1]) : 0;
        }
    }

    public int getCoefficient() {
        return coefficient;
    }

    public int getPower() {
        return power;
    }

    public int compareTo(Term o) {
        return Integer.valueOf(power).compareTo(Integer.valueOf(o.getPower()));
    }
}

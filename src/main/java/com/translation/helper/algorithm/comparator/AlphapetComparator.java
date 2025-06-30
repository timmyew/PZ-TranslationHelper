package com.translation.helper.algorithm.comparator;

import java.util.Comparator;

public class AlphapetComparator implements Comparator<String> {
    private final int LOWER = -1;
    private final int EQUAL = 0;
    private final int HIGHER = 1;

    @Override
    public int compare(String o1, String o2) {
        o1 = o1.toLowerCase();
        o2 = o2.toLowerCase();

        for (int i = 0; i < o1.length(); i++) {
            if (o2.length() - 1 < i){
                return LOWER;
            }

            if (o1.charAt(i) < o2.charAt(i)) {
                return LOWER;
            }
            else if (o1.charAt(i) == o2.charAt(i)) {
                return EQUAL;
            }
            else if (o1.charAt(i) > o2.charAt(i)) {
                return HIGHER;
            }
        }
        return EQUAL;
    }
}

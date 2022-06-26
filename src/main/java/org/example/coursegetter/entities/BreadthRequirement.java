package org.example.coursegetter.entities;

import java.util.ArrayList;
import java.util.List;

public class BreadthRequirement {

    private final double courseWeight;
    private final List<Integer> brFilled;

    public BreadthRequirement(String brqString, double courseWeight) {
        brFilled = new ArrayList<>();
        this.courseWeight = courseWeight;
        for (int i = 1; i <= 5; i++) {
            if (brqString.contains(Integer.toString(i))) {
                brFilled.add(i);
            }
        }

    }

    /**
     * Returns the credit weight this course contributes
     * to a particular breadth requirement.
     *
     * @param brCategory the br category to test
     * @return the amount of credits it contributes to the br
     */
    public double breadthWeight(int brCategory) {
        double divs = brFilled.size();
        if (brFilled.contains(brCategory)) {
            return courseWeight / divs;
        }
        return 0.0;  // otherwise
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        brFilled.forEach(n -> sb.append(String.format("%.1f credits in BR%d, ",
                breadthWeight(n), n)));
        if (sb.length() >= 2) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    /**
     * Keep track of the indices that are returned!!
     *
     * @return a double array of size 5, where each index
     * corresponds to the credits it contributes
     * to in each breadth requirement.
     * For example, ANT100Y1 should result in {0.0, 0.0, 0.5, 0.5, 0.0}
     */
    public double[] getBreadthContributions() {
        double[] soFar = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        if (brFilled.size() == 0)
            return soFar;
        // prevents division by zero errors.
        for (int i = 0; i < 5; i++) {
            soFar[i] = breadthWeight(i + 1);
        }
        return soFar;
    }
}

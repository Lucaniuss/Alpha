package gg.clouke.alpha.util.helper;

import lombok.experimental.UtilityClass;

/**
 * @author Clouke & Jonathan
 * @since 29.05.2022 18:00
 * All Rights Reserved
 */

@UtilityClass
public class AMath {

    /**
     * @return calculate hypot of arbitrary number of arguments
     */
    public double hypot(double... p) {
        return Math.sqrt(sumSquares(p));
    }

    /**
     * @return calculate hypot of arbitrary number of arguments
     */
    public double sumSquares(double... p) {
        double sum = 0;
        for (double i : p)
            sum += i * i;
        return sum;
    }

}

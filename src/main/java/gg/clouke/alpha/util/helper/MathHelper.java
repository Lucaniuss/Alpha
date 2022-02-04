package gg.clouke.alpha.util.helper;

import lombok.experimental.UtilityClass;

import java.util.Collection;

/**
 * @author Clouke
 * @since 04.02.2022 18:57
 * All Rights Reserved
 */

@UtilityClass
public class MathHelper {

    public double getAverage(final Collection<? extends Number> data) {
        return data.stream().mapToDouble(Number::doubleValue).average().orElse(0D);
    }

}

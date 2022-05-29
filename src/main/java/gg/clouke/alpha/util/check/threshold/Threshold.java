package gg.clouke.alpha.util.check.threshold;

/**
 * @author Clouke
 * @since 29.05.2022 20:04
 * © Alpha - All Rights Reserved
 */
public interface Threshold {

    static Threshold of(double defaultValue) {
        return new DefaultThreshold(defaultValue);
    }

    double setThreshold(double threshold);

    double increment();

    double decrement();

    double get();

    void reset();

}

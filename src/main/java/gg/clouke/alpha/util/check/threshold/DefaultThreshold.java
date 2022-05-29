package gg.clouke.alpha.util.check.threshold;

/**
 * @author Clouke
 * @since 29.05.2022 20:04
 * © Alpha - All Rights Reserved
 */
public class DefaultThreshold implements Threshold {

    private final double defaultValue;
    private double value;

    public DefaultThreshold(double defaultValue) {
        this.defaultValue = value = defaultValue;
    }

    @Override
    public double setThreshold(double threshold) {
        return this.value = threshold;
    }

    @Override
    public double increment() {
        return this.value++;
    }

    @Override
    public double decrement() {
        return this.value--;
    }

    @Override
    public double get() {
        return this.value;
    }

    @Override
    public void reset() {
        this.value = this.defaultValue;
    }
}

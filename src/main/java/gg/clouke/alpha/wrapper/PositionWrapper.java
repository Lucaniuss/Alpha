package gg.clouke.alpha.wrapper;

import lombok.Getter;
import org.bukkit.util.Vector;

/**
 * @author Clouke
 * @since 04.02.2022 17:59
 * All Rights Reserved
 */

public class PositionWrapper {

    @Getter
    private final double x, y, z;

    public PositionWrapper(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector toVector() {
        return new Vector(x, y, z);
    }

}

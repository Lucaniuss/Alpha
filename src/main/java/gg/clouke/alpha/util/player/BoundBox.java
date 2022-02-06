package gg.clouke.alpha.util.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import org.bukkit.util.Vector;

/**
 * @author Clouke
 * @since 04.02.2022 18:26
 * All Rights Reserved
 */

@Data
@AllArgsConstructor
public class BoundBox {

    private Vector min, max;

    public BoundBox(Vector vector, double width, double height, double depth) {
        min = new Vector(vector.getX() - width, vector.getY() - depth, vector.getZ() - width);
        max = new Vector(vector.getX() + width, vector.getY() + height, vector.getZ() + width);
    }

    /**
     * Expands the bounding box by {@param amount} in every direction
     * @param amount The amount to expand by
     * @return The expanded bounding box
     */
    public BoundBox expand(double amount) {
        Vector expansion = new Vector(amount, amount, amount);

        min.subtract(expansion);
        max.add(expansion);

        return new BoundBox(min, max);
    }

    /**
     * Offsets the bounding box
     * @param distance The distance to offset it by
     * @return The offset bounding box
     */
    public BoundBox offset(Vector distance) {
        min.add(distance);
        max.add(distance);

        return new BoundBox(min, max);
    }

    /**
     * Gets the distance to {@param location} using the min and max
     * @param location The location to get the distance to
     * @return The distance
     */
    public double distance(Vector location) {
        double dx = Math.min(Math.abs(location.getX() - min.getX()), Math.abs(location.getX() - max.getX()));
        double dz = Math.min(Math.abs(location.getZ() - min.getZ()), Math.abs(location.getZ() - max.getZ()));

        return dx * dx + dz * dz;
    }

    /**
     * @return A {@link AxisAlignedBB} instance of the bounding box
     */
    public AxisAlignedBB getAABB() {
        return new AxisAlignedBB(min.getX(), min.getY(), min.getZ(), max.getX(), max.getY(), max.getZ());
    }

    public BoundBox clone() {
        return new BoundBox(min, max);
    }

}

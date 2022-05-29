package gg.clouke.alpha.util.player;

/**
 * @author Clouke
 * @since 28.05.2022 17:55
 * © Alpha - All Rights Reserved
 */
public interface Human {

    double getX();
    double getY();
    double getZ();
    float getYaw();
    float getPitch();

    void move(double x, double y, double z);
    void move(double x, double y, double z, float yaw, float pitch);
    void move(Human human);

    Human expand(double x, double y, double z);
    Human contract(double x, double y, double z);
    Human interpolate(Human a, Human b, double percent);
    Human copy();

    float distance(Human other);
    double distanceTo(Human other);

}

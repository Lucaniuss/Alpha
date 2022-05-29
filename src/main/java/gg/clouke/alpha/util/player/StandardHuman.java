package gg.clouke.alpha.util.player;

import gg.clouke.alpha.util.helper.AMath;

/**
 * @author Clouke
 * @since 28.05.2022 17:56
 * © Alpha - All Rights Reserved
 */
public class StandardHuman implements Human {

    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    public StandardHuman() {}

    public StandardHuman(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public StandardHuman(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getZ() {
        return z;
    }

    @Override
    public float getYaw() {
        return yaw;
    }

    @Override
    public float getPitch() {
        return pitch;
    }

    @Override
    public void move(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void move(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    @Override
    public void move(Human human) {
        move(human.getX(), human.getY(), human.getZ(), human.getYaw(), human.getPitch());
    }

    @Override
    public Human expand(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    @Override
    public Human contract(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    @Override
    public Human interpolate(Human a, Human b, double percent) {
        return new StandardHuman(
                a.getX() + ((b.getX() - a.getX()) / percent),
                a.getY() + ((b.getY() - a.getY()) / percent),
                a.getZ() + ((b.getZ() - a.getZ()) / percent)
        );
    }

    @Override
    public Human copy() {
        return new StandardHuman(x, y, z, yaw, pitch);
    }

    @Override
    public float distance(Human other) {
        return (float) Math.sqrt(Math.pow(x - other.getX(), 2) + Math.pow(y - other.getY(), 2) + Math.pow(z - other.getZ(), 2));
    }

    @Override
    public double distanceTo(Human other) {
        final double dx = other.getX() - this.x;
        final double dy = other.getY() - this.y;
        final double dz = other.getZ() - this.z;
        return AMath.hypot(dx, dy, dz);
    }
}

package gg.clouke.alpha.profile.container.impl;

import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.profile.container.AbstractContainer;
import gg.clouke.alpha.profile.container.PacketContainer;
import gg.clouke.alpha.provider.network.impl.system.EPacket;
import gg.clouke.alpha.provider.network.impl.system.bound.server.CPacketFlying;
import gg.clouke.alpha.util.functions.Condition;
import lombok.Getter;

/**
 * @author Clouke
 * @since 29.05.2022 20:02
 * © Alpha - All Rights Reserved
 */

@Getter
public class MotionContainer extends AbstractContainer implements PacketContainer {

    private double x, y, z;
    private double lastX, lastY, lastZ;
    private int airTicks;
    private boolean falling, walkingBackwards;

    public MotionContainer(Profile profile) {
        super(profile);
    }

    @Override
    public void handle(EPacket<?> packet) {
        if (packet instanceof CPacketFlying) {
            final CPacketFlying wrapper = (CPacketFlying) packet;
            airTicks = wrapper.isOnGround() ? 0 : airTicks + 1;
            falling = lastY - y > 0.0;

            Condition.of(wrapper.isMoving(), () -> {
                lastX = x;
                lastY = y;
                lastZ = z;

                x = wrapper.getX();
                y = wrapper.getY();
                z = wrapper.getZ();

                walkingBackwards = (lastX - x > 0.0D) || (lastZ - z > 0.0D);
            });

        }
    }
}

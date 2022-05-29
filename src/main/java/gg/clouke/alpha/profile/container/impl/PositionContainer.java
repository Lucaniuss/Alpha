package gg.clouke.alpha.profile.container.impl;

import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.profile.container.AbstractContainer;
import gg.clouke.alpha.profile.container.PacketContainer;
import gg.clouke.alpha.provider.network.impl.system.EPacket;
import gg.clouke.alpha.provider.network.impl.system.bound.server.CPacketFlying;
import gg.clouke.alpha.provider.tick.TickProvider;
import gg.clouke.alpha.provider.tick.impl.TickTimer;
import gg.clouke.alpha.util.player.Human;
import gg.clouke.alpha.util.player.StandardHuman;
import gg.clouke.alpha.util.scheduler.SchedulerType;
import lombok.Getter;

/**
 * @author Clouke
 * @since 29.05.2022 12:18
 * © Alpha - All Rights Reserved
 */

@Getter
public final class PositionContainer extends AbstractContainer implements PacketContainer {

    private final Human client;
    private final TickTimer lastClientMove;

    public PositionContainer(Profile profile) {
        super(profile);
        this.client = new StandardHuman();
        this.lastClientMove = TickProvider.create(SchedulerType.REPEATING_ASYNC, 0);
    }

    @Override
    public void handle(EPacket<?> packet) {
        if (packet instanceof CPacketFlying) {
            final CPacketFlying wrapper = (CPacketFlying) packet;
            client.move(
                    wrapper.getX(),
                    wrapper.getY(),
                    wrapper.getZ(),
                    wrapper.getYaw(),
                    wrapper.getPitch()
            );

            if (wrapper.isMoving()) {
                lastClientMove.reset();
            }
        }
    }
}

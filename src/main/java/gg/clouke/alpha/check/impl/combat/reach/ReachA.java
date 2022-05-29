package gg.clouke.alpha.check.impl.combat.reach;

import gg.clouke.alpha.check.AbstractCheck;
import gg.clouke.alpha.check.BaseCheck;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.profile.container.impl.EntityContainer;
import gg.clouke.alpha.profile.container.impl.PositionContainer;
import gg.clouke.alpha.provider.network.impl.system.EPacket;
import gg.clouke.alpha.provider.network.impl.system.bound.server.CPacketFlying;
import gg.clouke.alpha.provider.network.impl.system.bound.server.CPacketUseEntity;
import gg.clouke.alpha.util.check.threshold.Threshold;
import gg.clouke.alpha.util.functions.Condition;
import gg.clouke.alpha.util.player.Human;

/**
 * @author Clouke
 * @since 28.05.2022 19:13
 * © Alpha - All Rights Reserved
 */

@BaseCheck(name = "Reach", type = "A", maxVl = 30)
public class ReachA extends AbstractCheck {

    private static final double PERCENT = 1.543;
    private final Threshold threshold;

    private Human target;
    private boolean sent;

    public ReachA(Profile profile) {
        super(profile);
        this.threshold = Threshold.of(3.0);
    }

    @Override
    public void handle(EPacket<?> packet) {
        if (packet instanceof CPacketFlying && sent) {
            final Human player = super.containers.get(PositionContainer.class).getClient();

            final Human interpolation = player.interpolate(player, target.expand(0.105F, 0.105F, 0.105F), PERCENT);
            final double distance = player.distanceTo(interpolation);

            debug(distance);
            Condition.of(distance > threshold.get() && distance <= 6.0, () -> alert("Distance: " + distance));

            threshold.reset();
            sent = false;
        } else if (packet instanceof CPacketUseEntity) {
            final CPacketUseEntity wrapper = (CPacketUseEntity) packet;
            final Human target = super.containers.get(EntityContainer.class).getEntities().get(wrapper.getEntityId());
            if (target != null) {
                this.target = target.copy();
                this.sent = true;
            }
        }
    }
}

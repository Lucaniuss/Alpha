package gg.clouke.alpha.check.impl.combat.range;

import gg.clouke.alpha.check.Check;
import gg.clouke.alpha.check.BaseCheck;
import gg.clouke.alpha.packet.Packet;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.tracker.PositionTracker;
import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;
import org.bukkit.util.Vector;

/**
 * @author Clouke
 * @since 04.02.2022 18:48
 * All Rights Reserved
 *
 * <p>
 * This class is a variance of a low reach detection with any sorts of ping
 * We take advantage of the flying packet to get the latest player tick
 * Distance can return 0 & 1's which means it is not close to 3 blocks of reach,
 * If they are close to a 3 block range distance, then the distance results will become more accurate.
 * @see PositionTracker
 */

@BaseCheck(name = "Range", type = "A", experimental = true)
public class RangeA extends Check {

    private long preTick;
    private boolean sent;

    public RangeA(final Profile profile) {
        super(profile);
    }

    @Override
    public void handle(final Packet packet) {
        if (packet.isUseEntity()) {
            if (isAttacking(packet)) {
                sent = true;
                preTick = System.currentTimeMillis();
            }
        } else if (packet.isFlying()) {
            if (sent) {

                final WrappedPacketInFlying wrapper = new WrappedPacketInFlying(packet.getRawPacket());

                final PositionTracker tracker = profile.getPositionTracker();
                final Vector player = tracker.toVector();

                long transTick = (System.currentTimeMillis() - preTick);

                double distance = tracker.getTrackedTarget()
                        .stream()
                        .mapToDouble(positions -> player.distance(positions.getX()) - 0.4031)
                        .min()
                        .orElse(0);

                if (distance == 0 || distance > 4.5D)
                    return;

                final double threshold = wrapper.isPosition() ? 3.009 : 3.04;

                debug("D: " + distance + " T: " + threshold + " TT: " + transTick);
                if (distance >= threshold) {
                    if (transTick < 1) {
                        alert("D: " + distance + " T: " + threshold);
                    }
                }

                sent = false;
            }

        }
    }
}

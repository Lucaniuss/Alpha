package gg.clouke.alpha.check.impl.combat.range;

import gg.clouke.alpha.check.BaseCheck;
import gg.clouke.alpha.check.Check;
import gg.clouke.alpha.network.packet.Packet;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.tracker.PositionTracker;
import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;
import org.bukkit.util.Vector;

/**
 * @author Clouke
 * @since 06.02.2022 23:15
 * All Rights Reserved
 */

@BaseCheck(name = "Range", type = "C", experimental = true, maxVl = 15)
public class RangeC extends Check {

    private boolean sent;

    public RangeC(final Profile profile) {
        super(profile);
    }

    @Override
    public void handle(final Packet packet) {
        if (packet.isUseEntity()) {
            if (isAttacking(packet)) {
                sent = true;
            }
        } else if (packet.isFlying()) {
            if (sent) {
                final WrappedPacketInFlying wrapper = new WrappedPacketInFlying(packet.getRawPacket());

                final Profile target = this.getTargetProfile();
                if (target == null) return;

                final PositionTracker tracker = profile.getPositionTracker();
                final Vector player = new Vector(wrapper.getX(), wrapper.getY(), wrapper.getZ());

                double distance = tracker
                        .getTrackedTarget()
                        .stream()
                        .filter(tick -> profile.getTicks() == target.getTicks()) // check if they are on the same tick
                        .mapToDouble(positions -> player.distance(positions.getX()) - 0.4)
                        .min()
                        .orElse(0);

                if (distance == 0 || distance > 6.5D)
                    return;

                debug(distance);
                if (distance > 3.0D) {
                    alert("D: " + distance);
                }

                sent = false;
            }
        }
    }
}

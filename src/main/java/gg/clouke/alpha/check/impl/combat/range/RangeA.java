package gg.clouke.alpha.check.impl.combat.range;

import gg.clouke.alpha.check.Check;
import gg.clouke.alpha.check.BaseCheck;
import gg.clouke.alpha.packet.Packet;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.tracker.PositionTracker;
import gg.clouke.alpha.wrapper.PositionWrapper;
import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;
import org.bukkit.util.Vector;

/**
 * @author Clouke
 * @since 04.02.2022 18:48
 * All Rights Reserved
 *
 * <p>
 * Reach Class with a transaction detection of 3.009 ~ 3.03
 * Using {@link PositionTracker} and wrapping into a Vector with {@link PositionWrapper}
 */

@BaseCheck(name = "Range", type = "A", experimental = true)
public class RangeA extends Check {

    private boolean sent;

    public RangeA(final Profile profile) {
        super(profile);
    }

    @Override
    public void handle(final Packet packet) {
        if (packet.isUseEntity()) {
            if (isAttacking(packet))
                sent = true;
        } else if (packet.isFlying()) {
            if (sent) {

                final WrappedPacketInFlying wrapper = new WrappedPacketInFlying(packet.getRawPacket());

                final PositionTracker tracker = profile.getPositionTracker();
                final Vector player = new PositionWrapper(tracker.getX(), tracker.getY(), tracker.getZ()).toVector();
                final Profile targetProfile = getTargetProfile();

                final PositionTracker targetTracker = targetProfile.getPositionTracker();
                final Vector target = new PositionWrapper(targetTracker.getX(), targetTracker.getY(), targetTracker.getZ()).toVector();

                final double distance = this.getDistance(player, target);
                final double threshold = wrapper.isPosition() ? 3.009 : 3.03;

                debug("D: " + distance + " T: " + threshold);
                if (distance >= threshold) {
                    alert("D: " + distance + " T: " + threshold);
                }

                sent = false;
            }
        }
    }
}

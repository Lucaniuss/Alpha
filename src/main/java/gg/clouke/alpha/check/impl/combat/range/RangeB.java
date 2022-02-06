package gg.clouke.alpha.check.impl.combat.range;

import gg.clouke.alpha.check.BaseCheck;
import gg.clouke.alpha.check.Check;
import gg.clouke.alpha.packet.Packet;
import gg.clouke.alpha.profile.Profile;
import org.bukkit.util.Vector;

/**
 * @author Clouke
 * @since 06.02.2022 16:32
 * All Rights Reserved
 */

@BaseCheck(name = "Range", type = "B", experimental = true)
public class RangeB extends Check {

    private boolean sent;

    public RangeB(final Profile profile) {
        super(profile);
    }

    @Override
    public void handle(final Packet packet) {
        if (packet.isUseEntity()) {
            if (isAttacking(packet)) {
                this.sent = true;
            }
        } else if (packet.isFlying() && sent) {
            if (this.getTargetProfile() == null) return;
            if (profile.getPing() >= 90) return;

            final Vector target = this.getTargetProfile().getPositionTracker().toVector()
                    .setY(0);
            final Vector player = profile.getPositionTracker().toVector()
                    .setY(0);

            final boolean equal = profile.getTicks() == this.getTargetProfile().getTicks();
            final double collision = player.distance(target) - 1.4031;

            if (collision >= 6D) return;

            debug("C: " + collision + " E: " + equal);
            if (collision >= 3.3D && equal) {
                alert(collision);
            }

            sent = false;
        }
    }
}

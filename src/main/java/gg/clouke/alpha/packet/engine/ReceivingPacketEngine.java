package gg.clouke.alpha.packet.engine;

import gg.clouke.alpha.packet.Packet;
import gg.clouke.alpha.profile.Profile;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ReceivingPacketEngine {

    public void update(final Profile profile, final Packet packet) {
        if (packet.isFlying()) {
            profile.getPositionTracker().update(packet);
        }
        if (packet.isUseEntity()) {
            profile.getCombatTracker().update(packet);
        }

        profile.getChecks().forEach(check -> check.handle(packet));
    }

}

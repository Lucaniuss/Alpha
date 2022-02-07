package gg.clouke.alpha.network.packet.engine;

import gg.clouke.alpha.network.packet.Packet;
import gg.clouke.alpha.profile.Profile;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class SendingPacketEngine {

    public void update(final Profile data, final Packet packet) {
        data.getChecks().forEach(check -> check.handle(packet));
    }

}

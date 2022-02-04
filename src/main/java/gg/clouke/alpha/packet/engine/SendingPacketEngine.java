package gg.clouke.alpha.packet.engine;

import gg.clouke.alpha.packet.Packet;
import gg.clouke.alpha.profile.Profile;
import io.github.retrooper.packetevents.event.impl.PacketPlaySendEvent;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class SendingPacketEngine {
    public void process(final Profile data, final Packet packet) {
        data.getChecks().forEach(check -> check.handle(packet));
    }
}

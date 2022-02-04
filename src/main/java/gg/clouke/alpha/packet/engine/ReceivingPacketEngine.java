package gg.clouke.alpha.packet.engine;

import gg.clouke.alpha.packet.Packet;
import gg.clouke.alpha.profile.Profile;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class ReceivingPacketEngine {
    public void process(final Profile data, final Packet packet) {
        if (PacketType.Play.Client.Util.isInstanceOfFlying(packet.getPacketId())) {
            final WrappedPacketInFlying wrapper = new WrappedPacketInFlying(packet.getRawPacket());

            data.getPositionProcessor().handle(wrapper);
        }

        data.getChecks().forEach(check -> check.handle(packet));
    }
}

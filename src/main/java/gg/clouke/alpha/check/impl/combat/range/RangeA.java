package gg.clouke.alpha.check.impl.combat.range;

import gg.clouke.alpha.check.Check;
import gg.clouke.alpha.check.api.BaseCheck;
import gg.clouke.alpha.packet.Packet;
import gg.clouke.alpha.profile.Profile;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.event.impl.PacketPlaySendEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import org.bukkit.Location;

@BaseCheck(name = "Range", type = "A")
public final class RangeA extends Check {

    private boolean sent;

    public RangeA(final Profile data) {
        super(data);
    }

    @Override
    public void handle(final Packet packet) {
        if (packet.isUseEntity()) {
            if (isAttacking(packet)) {
                sent = true;
            }
        } else if (packet.isFlying()) {
            if (!sent) return;

            sent = false;
        }
    }
}

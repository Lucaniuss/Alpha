package gg.clouke.alpha.provider.network.impl.system.bound.server;

import gg.clouke.alpha.provider.network.impl.system.EPacket;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;

/**
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */
@Getter
public class CPacketClientStatus extends EPacket<PacketPlayInClientCommand> {
    protected State status;

    @Override
    public void handle(PacketDataSerializer serializer) {
        status = State.values()[serializer.a(PacketPlayInClientCommand.EnumClientCommand.class).ordinal()];
    }

    public enum State {
        PERFORM_RESPAWN, REQUEST_STATS, OPEN_INVENTORY_ACHIEVEMENT;
    }
}

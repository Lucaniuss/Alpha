package gg.clouke.alpha.provider.network.impl.system.bound.server;

import gg.clouke.alpha.provider.network.impl.system.EPacket;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayInCloseWindow;

/**
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */
@Getter
public class CPacketCloseWindow extends EPacket<PacketPlayInCloseWindow> {
    protected int windowId;

    @Override
    public void handle(PacketDataSerializer serializer) {
        windowId = serializer.readByte();
    }
}


package gg.clouke.alpha.provider.network.impl.system.bound.server;

import gg.clouke.alpha.provider.network.impl.system.EPacket;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;

/**
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */

public class CPacketAnimation extends EPacket<PacketPlayInArmAnimation> {

    @Override
    public void handle(PacketDataSerializer serializer) {}

}
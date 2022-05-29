package gg.clouke.alpha.provider.network.impl.system.bound.client;

import gg.clouke.alpha.provider.network.impl.system.EPacket;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutExplosion;

/**
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */
public class SPacketExplosion extends EPacket<PacketPlayOutExplosion> {
    @Override
    public void handle(PacketDataSerializer serializer) {

    }
}

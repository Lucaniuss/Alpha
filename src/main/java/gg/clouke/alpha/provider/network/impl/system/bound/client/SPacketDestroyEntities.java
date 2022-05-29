package gg.clouke.alpha.provider.network.impl.system.bound.client;

import gg.clouke.alpha.provider.network.impl.system.EPacket;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;

/**
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */
@Getter
public class SPacketDestroyEntities extends EPacket<PacketPlayOutEntityDestroy> {
    protected int[] ids;

    @Override
    public void handle(PacketDataSerializer serializer) {
        ids = new int[serializer.e()];

        for (int i = 0; i < ids.length; ++i) {
            ids[i] = serializer.e();
        }
    }
}

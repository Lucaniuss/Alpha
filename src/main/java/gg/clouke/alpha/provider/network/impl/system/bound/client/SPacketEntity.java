package gg.clouke.alpha.provider.network.impl.system.bound.client;

import gg.clouke.alpha.provider.network.impl.system.EPacket;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity;

/**
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */
@Getter
public class SPacketEntity extends EPacket<PacketPlayOutEntity> {
    protected int entityId;

    protected byte posX, posY, posZ;

    protected byte yaw, pitch;

    @Override
    public void handle(PacketDataSerializer serializer) {
        entityId = serializer.e();
    }
}

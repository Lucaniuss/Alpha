package gg.clouke.alpha.provider.network.impl.system.bound.client;

import gg.clouke.alpha.provider.network.impl.system.EPacket;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityVelocity;

/**
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */
@Getter
public class SPacketEntityVelocity extends EPacket<PacketPlayOutEntityVelocity> {
    protected int entityId;

    protected short motionX, motionY, motionZ;

    @Override
    public void handle(PacketDataSerializer serializer) {
        entityId = serializer.e();

        motionX = serializer.readShort();
        motionY = serializer.readShort();
        motionZ = serializer.readShort();
    }
}

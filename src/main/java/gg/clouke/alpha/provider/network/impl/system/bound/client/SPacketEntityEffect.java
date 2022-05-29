package gg.clouke.alpha.provider.network.impl.system.bound.client;

import gg.clouke.alpha.provider.network.impl.system.EPacket;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEffect;

/**
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */
@Getter
public class SPacketEntityEffect extends EPacket<PacketPlayOutEntityEffect> {
    protected int entityId;

    protected byte effectId, amplifier;

    protected int duration;

    @Override
    public void handle(PacketDataSerializer serializer) {
        entityId = serializer.e();

        effectId = serializer.readByte();
        amplifier = serializer.readByte();

        duration = serializer.e();
    }
}

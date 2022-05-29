package gg.clouke.alpha.provider.network.impl.system.bound.server;

import gg.clouke.alpha.provider.network.impl.system.EPacket;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;

/**
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */
@Getter
public class CPacketFlying extends EPacket<PacketPlayInFlying> {
    protected double x, y, z;

    protected float yaw, pitch;

    protected boolean onGround, moving, rotating;

    @Override
    public void handle(PacketDataSerializer serializer) {
        onGround = serializer.readUnsignedByte() != 0;
    }
}

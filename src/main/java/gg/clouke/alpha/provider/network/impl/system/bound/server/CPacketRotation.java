package gg.clouke.alpha.provider.network.impl.system.bound.server;

import net.minecraft.server.v1_8_R3.PacketDataSerializer;

/**
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */
public class CPacketRotation extends CPacketFlying {

    @Override
    public void handle(PacketDataSerializer serializer) {
        yaw = serializer.readFloat();
        pitch = serializer.readFloat();

        rotating = true;

        super.handle(serializer);
    }
}

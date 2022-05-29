package gg.clouke.alpha.provider.network.impl.system.bound.client;

import net.minecraft.server.v1_8_R3.PacketDataSerializer;

/**
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */
public class SPacketEntityLookMove extends SPacketEntity {

    @Override
    public void handle(PacketDataSerializer serializer) {
        super.handle(serializer);

        posX = serializer.readByte();
        posY = serializer.readByte();
        posZ = serializer.readByte();

        yaw = serializer.readByte();
        pitch = serializer.readByte();
    }
}

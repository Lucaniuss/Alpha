package gg.clouke.alpha.provider.network.impl.system;

import lombok.Data;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;

/**
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */
@Data
public abstract class EPacket<T extends Packet<?>> {
    private long timestamp;
    private int id;

    // Handles a PacketDataSerializer to avoid the use of reflection
    public abstract void handle(PacketDataSerializer serializer);
}
package gg.clouke.alpha.provider.network.impl.system.bound.client;

import gg.clouke.alpha.provider.network.impl.system.EPacket;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutUpdateAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */
@Getter
public class SPacketUpdateAttributes extends EPacket<PacketPlayOutUpdateAttributes> {
    protected int entityId;

    private final List<Snapshot> snapshots = new ArrayList<>();

    @Override
    public void handle(PacketDataSerializer serializer) {
        entityId = serializer.e();

        int i = serializer.readInt();

        for(int j = 0; j < i; ++j) {
            String name = serializer.c(64);
            double baseValue = serializer.readDouble();
            int k = serializer.e();

            for(int l = 0; l < k; ++l) {
                UUID var10 = serializer.g();

                serializer.readDouble();
                serializer.readByte();
            }

            snapshots.add(new Snapshot(name, baseValue));
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class Snapshot {
        private final String name;

        private final double baseValue;
    }
}

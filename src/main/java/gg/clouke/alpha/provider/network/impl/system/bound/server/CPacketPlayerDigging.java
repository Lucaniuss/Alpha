package gg.clouke.alpha.provider.network.impl.system.bound.server;

import gg.clouke.alpha.provider.network.impl.system.EPacket;
import gg.clouke.alpha.util.Direction;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.EnumDirection;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import org.bukkit.util.Vector;

/**
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */
@Getter
public class CPacketPlayerDigging extends EPacket<PacketPlayInBlockDig> {
    protected Vector blockPos;

    protected Direction direction;

    protected Action action;

    @Override
    public void handle(PacketDataSerializer serializer) {
        action = Action.values()[serializer.a(PacketPlayInBlockDig.EnumPlayerDigType.class).ordinal()];

        BlockPosition blockPosition = serializer.c();

        blockPos = new Vector(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());

        direction = Direction.values()[EnumDirection.fromType1(serializer.readUnsignedByte()).ordinal()];
    }

    public enum Action {
        START_DESTROY_BLOCK,
        ABORT_DESTROY_BLOCK,
        STOP_DESTROY_BLOCK,
        DROP_ALL_ITEMS,
        DROP_ITEM,
        RELEASE_USE_ITEM
    }
}

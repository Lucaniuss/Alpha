package gg.clouke.alpha.provider.network.impl.system.bound.server;

import gg.clouke.alpha.provider.network.impl.system.EPacket;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.io.IOException;

/**
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */
@Getter
public class CPacketBlockPlacement extends EPacket<PacketPlayInBlockPlace> {
    protected Vector blockPos;

    protected int placedBlockDirection;

    protected ItemStack itemStack;

    protected float facingX;
    protected float facingY;
    protected float facingZ;

    @Override
    public void handle(PacketDataSerializer packetdataserializer) {
        BlockPosition blockPosition = packetdataserializer.c();

        blockPos = new Vector(blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());

        placedBlockDirection = packetdataserializer.readUnsignedByte();

        try {
            itemStack = CraftItemStack.asBukkitCopy(packetdataserializer.i());
        } catch (IOException e) {
            e.printStackTrace();
        }

        facingX = (float) packetdataserializer.readUnsignedByte() / 16.0F;
        facingY = (float) packetdataserializer.readUnsignedByte() / 16.0F;
        facingZ = (float) packetdataserializer.readUnsignedByte() / 16.0F;
    }
}
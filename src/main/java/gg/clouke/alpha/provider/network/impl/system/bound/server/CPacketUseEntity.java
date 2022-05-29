package gg.clouke.alpha.provider.network.impl.system.bound.server;

import gg.clouke.alpha.provider.network.impl.system.EPacket;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import java.lang.ref.WeakReference;

/**
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */
@Getter
public class CPacketUseEntity extends EPacket<PacketPlayInUseEntity> {
    protected int entityId;

    protected EnumEntityUseAction action;

    protected Vector hitVec;

    protected WeakReference<Entity> entity;

    @Override
    public void handle(PacketDataSerializer serializer) {
        // PacketDataSerializer#e = readVarInt
        entityId = serializer.e();

        action = EnumEntityUseAction.values()[serializer.a(PacketPlayInUseEntity.EnumEntityUseAction.class).ordinal()];

        if (action == EnumEntityUseAction.INTERACT_AT) {
            hitVec = new Vector(serializer.readFloat(), serializer.readFloat(), serializer.readFloat());
        }
    }

    public Entity getEntity(World world) {
        if (entity == null) {
            net.minecraft.server.v1_8_R3.Entity entity = ((CraftWorld) world).getHandle().a(entityId);

            if (entity == null) {
                this.entity = new WeakReference<>(null);
            } else {
                this.entity = new WeakReference<>(entity.getBukkitEntity());
            }
        }

        return entity.get();
    }

    public enum EnumEntityUseAction {
        INTERACT,
        ATTACK,
        INTERACT_AT
    }
}

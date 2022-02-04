package gg.clouke.alpha.check;

import gg.clouke.alpha.packet.Packet;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.event.impl.PacketPlaySendEvent;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import lombok.Getter;
import gg.clouke.alpha.profile.Profile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

@Getter
public abstract class Check {

    protected final Profile data;
    private double buffer;

    public Check(final Profile data) {
        this.data = data;
    }

    /**
     * This is our packet event system.
     */
    public abstract void handle(final Packet packet);

    /**
     * This our buffer system.
     */
    public final double increaseBuffer() {
        return buffer = Math.min(10000, buffer + 1);
    }
    public final double increaseBufferBy(final double amount) {
        return buffer = Math.min(10000, buffer + amount);
    }
    public final double decreaseBuffer() {
        return buffer = Math.max(0, buffer - 1);
    }
    public final double decreaseBufferBy(final double amount) {
        return buffer = Math.max(0, buffer - amount);
    }
    public final void resetBuffer() {
        buffer = 0;
    }
    public final void setBuffer(final double amount) {
        buffer = amount;
    }
    public final void multiplyBuffer(final double multiplier) {
        buffer *= multiplier;
    }


    public final void debug(final Object object) {
        data.getPlayer().sendMessage(ChatColor.RED + "[Alpha-Debug] " + ChatColor.GRAY + object);
    }

    public final void broadcast(final Object object) {
        Bukkit.broadcastMessage(ChatColor.RED + "[Alpha-Debug] " + ChatColor.GRAY + object);
    }

    protected boolean isAttacking(Packet packet) {
        final WrappedPacketInUseEntity entity = new WrappedPacketInUseEntity(packet.getRawPacket());
        return (entity.getAction() == WrappedPacketInUseEntity.EntityUseAction.ATTACK);
    }

}

package gg.clouke.alpha.check;

import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.module.punishment.BanModule;
import gg.clouke.alpha.network.packet.Packet;
import gg.clouke.alpha.util.player.BoundBox;
import gg.clouke.alpha.module.alert.AlertWrapper;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import lombok.Getter;
import gg.clouke.alpha.profile.Profile;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

@Getter
public abstract class Check {

    protected final Profile profile;
    protected double buffer;
    private int vl;

    @Setter
    public boolean debug;

    public Check(final Profile profile) {
        this.profile = profile;
    }

    public abstract void handle(final Packet packet);

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

    public BaseCheck getBaseCheck() {
        if (this.getClass().isAnnotationPresent(BaseCheck.class)) {
            return this.getClass().getAnnotation(BaseCheck.class);
        } else {
            System.err.println("@BaseCheck annotation hasn't been added to the class " + this.getClass().getSimpleName() + ".");
            return null;
        }
    }

    public final void debug(final Object object) {
        if (debug) profile.getPlayer().sendMessage(ChatColor.RED + "[Alpha-Debug] " + ChatColor.GRAY + object);
    }

    protected void alert(final Object obj) {
        this.vl++;
        new AlertWrapper(this, profile, obj);

        if (this.vl > getBaseCheck().maxVl()) {
            new BanModule(profile);
        }
    }

    protected boolean isAttacking(Packet packet) {
        final WrappedPacketInUseEntity entity = new WrappedPacketInUseEntity(packet.getRawPacket());
        return (entity.getAction() == WrappedPacketInUseEntity.EntityUseAction.ATTACK);
    }

    protected int ticks = Alpha.INSTANCE.getTickTracker().getTicks();

    protected Profile getTargetProfile() {
        final Player player = (Player) profile.getCombatTracker().getTarget();
        if (player == null)
            return null;

        return Alpha.INSTANCE.getProfileRouter().get(player);
    }

    /**
     * <p>
     * This function is used for reach checks to get a well calculated distance
     *
     * @param player Players Vector Location
     * @param target Targets Vector Location
     * @return Returning a better calculated distance from A to B {@link BoundBox}
     */
    public double getDistance(Vector player, Vector target) {
        BoundBox bb = new BoundBox(target, 0.403125, 1.905, 0.1001);

        return bb.distance(player);
    }

}

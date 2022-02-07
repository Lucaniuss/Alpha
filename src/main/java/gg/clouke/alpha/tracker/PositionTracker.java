package gg.clouke.alpha.tracker;

import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.network.packet.Packet;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.util.Pair;
import gg.clouke.alpha.util.list.EvictingList;
import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * @author Clouke
 * @since 04.02.2022 18:08
 * All Rights Reserved
 */

@Data
public final class PositionTracker {

    private final Alpha plugin = Alpha.INSTANCE;

    private final Profile profile;
    private final EvictingList<Pair<Vector, Integer>> trackedTarget;

    private double x, y, z;

    public PositionTracker(final Profile profile) {
        this.profile = profile;
        this.trackedTarget = new EvictingList<>(40);
    }

    public void update(final Packet packet) {
        final WrappedPacketInFlying wrapper = new WrappedPacketInFlying(packet.getRawPacket());

        double increment = wrapper.isPosition() ? 0.0065 : 0.005; // Predicted Server Movement

        this.x = wrapper.getX() + increment;
        this.y = wrapper.getY();
        this.z = wrapper.getZ() + increment;

        final Entity eTarget = profile.getCombatTracker().getTarget();
        if (eTarget != null) {
            if (eTarget instanceof Player) {
                final Profile target = Alpha.INSTANCE.getProfileRouter().get((Player) profile.getCombatTracker().getTarget());
                trackedTarget.add(new Pair<>(target.getPositionTracker().toVector(), target.getTicks()));
            }
        } else {
            trackedTarget.clear();
        }

    }

    public Vector toVector() {
        return new Vector(x, y, z);
    }

    public Location toLocation() {
        final World world = profile.getPlayer().getWorld();
        final float yaw = profile.getPlayer().getLocation().getYaw();
        final float pitch = profile.getPlayer().getLocation().getPitch();

        return new Location(world, x, y, z, yaw, pitch);
    }

}

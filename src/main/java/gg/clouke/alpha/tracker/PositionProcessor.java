package gg.clouke.alpha.tracker;

import gg.clouke.alpha.profile.Profile;
import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter @Setter
public final class PositionProcessor {

    private final Profile data;
    private Location to, from;

    private double x, y, z;

    public PositionProcessor(final Profile data) {
        this.data = data;

        // To prevent null pointer exceptions.
        to = data.getPlayer().getLocation();
        from = data.getPlayer().getLocation();
    }

    public void handle(final WrappedPacketInFlying wrapper) {
        if (wrapper.isPosition()) {
            this.x = wrapper.getX();
            this.y = wrapper.getY();
            this.z = wrapper.getZ();

            this.from = to;
            to = new Location(data.getPlayer().getWorld(), x, y, z);
        }
    }
}

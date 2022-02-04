package gg.clouke.alpha.tracker;

import gg.clouke.alpha.packet.Packet;
import gg.clouke.alpha.profile.Profile;
import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;
import lombok.Data;

/**
 * @author Clouke
 * @since 04.02.2022 18:08
 * All Rights Reserved
 */

@Data
public final class PositionTracker {

    private final Profile profile;
    private double x, y, z;

    public PositionTracker(final Profile profile) {
        this.profile = profile;
    }

    public void update(final Packet packet) {
        if (packet.isFlying()) {

            final WrappedPacketInFlying wrapper = new WrappedPacketInFlying(packet.getRawPacket());

            double increment = wrapper.isPosition() ? 0.007 : 0.05; // Predicted Server Position

            this.x = wrapper.getX() + increment;
            this.y = wrapper.getY() + increment;
            this.z = wrapper.getZ() + increment;

        }
    }
}

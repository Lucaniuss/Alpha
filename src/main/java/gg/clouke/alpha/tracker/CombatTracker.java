package gg.clouke.alpha.tracker;

import gg.clouke.alpha.packet.Packet;
import gg.clouke.alpha.profile.Profile;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import lombok.Data;
import org.bukkit.entity.Entity;

/**
 * @author Clouke
 * @since 04.02.2022 18:17
 * All Rights Reserved
 */

@Data
public class CombatTracker {

    private final Profile profile;

    private Entity target;
    private long lastHit;

    public CombatTracker(final Profile profile) {
        this.profile = profile;
    }

    public void update(final Packet packet) {
        if (packet.isUseEntity()) {
            final WrappedPacketInUseEntity wrapper = new WrappedPacketInUseEntity(packet.getRawPacket());
            if (wrapper.getAction() != WrappedPacketInUseEntity.EntityUseAction.ATTACK) return;

            this.target = wrapper.getEntity();
            this.lastHit = System.currentTimeMillis();
        }
    }

}

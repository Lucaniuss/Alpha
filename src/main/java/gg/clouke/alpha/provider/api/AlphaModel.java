package gg.clouke.alpha.provider.api;

import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.AlphaAPI;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.provider.event.EventListener;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author Clouke
 * @since 28.05.2022 23:23
 * © Alpha - All Rights Reserved
 */
public class AlphaModel implements AlphaAPI {

    private final Alpha plugin;

    public AlphaModel() {
        this.plugin = Alpha.getInstance();
    }

    @Override
    public void sendPacket(Player player, Packet<?> packet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void sendPacket(Profile profile, Packet<?> packet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void subscribe(EventListener listener) {
        this.plugin.getEventProvider().subscribe(listener);
    }

    @Override
    public void unsubscribe(EventListener listener) {
        this.plugin.getEventProvider().unsubscribe(listener);
    }

    @Override
    public Profile getProfile(UUID uuid) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Profile getProfile(Player player) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

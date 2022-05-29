package gg.clouke.alpha;

import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.provider.event.EventListener;
import gg.clouke.alpha.util.annotations.DoNotMock;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * @author Clouke
 * @since 28.05.2022 23:22
 * © Alpha - All Rights Reserved
 */

@DoNotMock("Use AlphaAPI api = new AlphaModel();")
public interface AlphaAPI {

    static Alpha get() {
        return Alpha.getInstance();
    }

    /**
     * Send a packet to a player
     */
    void sendPacket(Player player, Packet<?> packet);

    /**
     * Send a packet to a player
     */
    void sendPacket(Profile profile, Packet<?> packet);

    /**
     * Subscribe an event listener to the event bus.
     */
    void subscribe(EventListener listener);

    /**
     * Unsubscribe an event listener from the event bus.
     */
    void unsubscribe(EventListener listener);

    /**
     * @throws IllegalArgumentException if the player is not registered
     * @return Returns the profile of the given UUID
     */
    Profile getProfile(UUID uuid) throws IllegalArgumentException;

    /**
     * @throws IllegalArgumentException if the player is not registered
     * @return Returns the profile of the given player
     */
    Profile getProfile(Player player) throws IllegalArgumentException;

}

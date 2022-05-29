package gg.clouke.alpha.profile.impl;

import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.provider.event.EventListener;
import gg.clouke.alpha.util.bukkit.AbstractListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Clouke
 * @since 29.05.2022 15:32
 * © Alpha - All Rights Reserved
 */
public final class BukkitProfileInjector extends AbstractListener {

    @EventHandler
    public void handleConnection(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        this.plugin.getProfileProvider().create(player); // Create the profile

        Profile profile = this.plugin.getProfileProvider().get(player);
        this.plugin.getNetworkProvider().inject(profile, player); // Inject to the packet handler
    }

    @EventHandler
    public void handleDisconnection(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Profile profile = plugin.getProfileProvider().get(player);
        profile.getTrackedListeners().forEach(EventListener::dispose);

        this.plugin.getNetworkProvider().eject(player); // Eject from the packet handler
        this.plugin.getThreadWatcher().remove(profile); // Remove the indexing of the profile
        this.plugin.getProfileProvider().remove(player); // Remove the profile
    }

}

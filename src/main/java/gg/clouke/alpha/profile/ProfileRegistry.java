package gg.clouke.alpha.profile;

import gg.clouke.alpha.Alpha;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class ProfileRegistry implements Listener {

    /**
     * <p>
     * Add player to {@link ProfileRouter}'s hashmap
     *
     * @param e {@link PlayerQuitEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void handleConnection(final PlayerJoinEvent e) {
        Alpha.INSTANCE.getProfileRouter().add(e.getPlayer());
    }

    /**
     * <p>
     * Remove player from {@link ProfileRouter}'s hashmap
     *
     * @param e {@link PlayerQuitEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void handleQuit(final PlayerQuitEvent e) {
        Alpha.INSTANCE.getProfileRouter().remove(e.getPlayer());
    }

}

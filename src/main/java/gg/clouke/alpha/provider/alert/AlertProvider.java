package gg.clouke.alpha.provider.alert;

import gg.clouke.alpha.check.AbstractCheck;
import gg.clouke.alpha.profile.Profile;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.concurrent.ExecutorService;

/**
 * @author Clouke
 * @since 29.05.2022 15:04
 * © Alpha - All Rights Reserved
 */
public interface AlertProvider {

    /**
     * Call this method to send an alert to all subscribed players
     */
    void flag(Profile profile, AbstractCheck check, String data);

    /**
     * Subscribe a player to view alerts
     */
    void subscribe(Player player);

    /**
     * Unsubscribe a player from viewing alerts
     */
    void unsubscribe(Player player);

    /**
     * @return Returns true if the player is subscribed to alerts
     */
    boolean isSubscribed(Player player);

    /**
     * @return Returns a set of all subscribed players
     */
    Set<Player> getSubscribers();

    /**
     * @return Returns the executor service used to send alerts
     */
    ExecutorService getExecutor();

}

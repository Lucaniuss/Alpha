package gg.clouke.alpha.profile;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

/**
 * @author Clouke
 * @since 28.05.2022 22:05
 * © Alpha - All Rights Reserved
 * Every method throws {@link IllegalArgumentException} if the given parameter is null or empty.
 */
public interface ProfileProvider {

    /**
     * @param player Player to check
     * @return The Profile of the player
     */
    Profile get(Player player) throws IllegalStateException;

    /**
     * @param uuid UUID of the player
     * @return The Profile of the player
     */
    Profile get(UUID uuid) throws IllegalStateException;

    /**
     * @param id EntityID of the player
     * @return The Profile of the player
     */
    Profile get(int id) throws NullPointerException;

    /**
     * @param player Player to register
     */
    void create(Player player) throws IllegalStateException;

    /**
     * @param player Player to unregister
     */
    void remove(Player player) throws IllegalStateException;

    /**
     * @param player Player to check
     */
    boolean isRegistered(Player player);

    /**
     * @return All registered players
     */
    Collection<Profile> getAll();

}

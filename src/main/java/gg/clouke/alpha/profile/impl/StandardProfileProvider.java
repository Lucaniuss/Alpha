package gg.clouke.alpha.profile.impl;

import com.google.common.base.Preconditions;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.profile.ProfileProvider;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Clouke
 * @since 28.05.2022 21:59
 * © Alpha - All Rights Reserved
 */
public final class StandardProfileProvider implements ProfileProvider {

    private final Map<UUID, Profile> profiles;

    public StandardProfileProvider() {
        this.profiles = new HashMap<>();
    }

    @Override
    public Profile get(Player player) throws IllegalStateException {
        UUID uuid = player.getUniqueId();
        Preconditions.checkState(this.profiles.containsKey(uuid), "The player is not registered");
        return this.profiles.get(uuid);
    }

    @Override
    public Profile get(UUID uuid) throws IllegalStateException {
        Preconditions.checkState(this.profiles.containsKey(uuid), "The player is not registered");
        return this.profiles.get(uuid);
    }

    @Override
    public Profile get(int id) throws NullPointerException {
        for (Profile profile : this.profiles.values()) {
            if (profile.getPlayer().getEntityId() == id) {
                return profile;
            }
        }

        return null;
    }

    @Override
    public void create(Player player) {
        Preconditions.checkState(!this.profiles.containsKey(player.getUniqueId()), "The player is already registered");
        this.profiles.put(player.getUniqueId(), new StandardProfile(player));
    }

    @Override
    public void remove(Player player) throws IllegalStateException {
        Preconditions.checkState(this.profiles.containsKey(player.getUniqueId()), "The player is not registered");
        this.profiles.remove(player.getUniqueId());
    }

    @Override
    public boolean isRegistered(Player player) {
        return this.profiles.containsKey(player.getUniqueId());
    }

    @Override
    public Collection<Profile> getAll() {
        return this.profiles.values();
    }
}

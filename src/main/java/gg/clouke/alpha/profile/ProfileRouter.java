package gg.clouke.alpha.profile;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public final class ProfileRouter {

    private final ConcurrentHashMap<UUID, Profile> profiles = new ConcurrentHashMap<>();

    /**
     * <p>
     * Add {@link Player} to the profiles hashmap
     * @param player Issued player
     */
    public void add(final Player player) {
        profiles.put(player.getUniqueId(), new Profile(player));
    }

    /**
     * <p>
     * Remove {@link Player} from the profiles hashmap
     * @param player Issued player
     */
    public void remove(final Player player) {
        profiles.remove(player.getUniqueId());
    }

    /**
     * <p>
     * Get the profile of {@link Player}
     * @param player Issued player
     * @return Returns the profile map of the issued player
     */
    public Profile get(final Player player) {
        return profiles.get(player.getUniqueId());
    }

    public Collection<Profile> getAllProfiles() {
        return profiles.values();
    }

}

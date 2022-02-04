package gg.clouke.alpha.profile;

import lombok.Getter;
import org.bukkit.entity.Player;

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
     * @return Returns the profile map of the issued player, if they are not in the hashmap we will throw a new NullPtr
     */
    public Profile get(final Player player) {
        if (profiles.get(player.getUniqueId()) == null)
            throw new NullPointerException("Profile cannot be null");

        return profiles.get(player.getUniqueId());
    }
}

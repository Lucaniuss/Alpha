package gg.clouke.alpha.provider.network;

import gg.clouke.alpha.profile.Profile;
import org.bukkit.entity.Player;

/**
 * @author Clouke
 * @since 28.05.2022 18:43
 * © Alpha - All Rights Reserved
 *
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */
public interface NetworkProvider {

    /**
     * Injects the player into the network handler
     */
    void inject(Profile profile, Player player);

    /**
     * Ejects the player from the network handler
     */
    void eject(Player player);

}

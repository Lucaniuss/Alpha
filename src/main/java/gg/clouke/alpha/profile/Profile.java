package gg.clouke.alpha.profile;

import gg.clouke.alpha.check.AbstractCheck;
import gg.clouke.alpha.profile.container.ContainerService;
import gg.clouke.alpha.provider.event.EventListener;
import gg.clouke.alpha.provider.thread.ThreadProvider;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/**
 * @author Clouke
 * @since 28.05.2022 21:58
 * © Alpha - All Rights Reserved
 */
public interface Profile {

    /**
     * @return Returns the {@link Player}
     */
    Player getPlayer();

    /**
     * @return Returns the {@link UUID} of the player
     */
    UUID getUniqueId();

    /**
     * @return Returns the checks of the player
     */
    List<AbstractCheck> getChecks();

    /**
     * @return Returns the profile's {@link ContainerService}
     */
    ContainerService getContainerService();

    /**
     * @return Returns the profile's {@link ThreadProvider}
     */
    ThreadProvider getThreadProvider();

    /**
     * @return Returns the list of kept events
     */
    List<EventListener> getTrackedListeners();

    /**
     * @param listener {@link EventListener} to keep track of, so we can dispose when they have left
     */
    void keepTrackOf(EventListener listener);

    /**
     * @return Returns the NMS handle of the player
     */
    EntityPlayer getHandle();

}

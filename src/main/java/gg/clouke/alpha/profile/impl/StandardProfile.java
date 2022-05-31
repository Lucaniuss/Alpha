package gg.clouke.alpha.profile.impl;

import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.check.AbstractCheck;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.profile.container.ContainerService;
import gg.clouke.alpha.profile.container.standard.StandardContainerService;
import gg.clouke.alpha.provider.event.EventListener;
import gg.clouke.alpha.provider.thread.ThreadProvider;
import gg.clouke.alpha.provider.thread.impl.ThreadProviderFactory;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Clouke
 * @since 28.05.2022 22:03
 * © Alpha - All Rights Reserved
 */
public final class StandardProfile implements Profile {

    private final Player player;
    private final UUID uuid;
    private final ContainerService containerService;
    private final List<AbstractCheck> checks;
    private final ThreadProvider threadProvider;
    private final List<EventListener> trackedEvents;

    public StandardProfile(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.trackedEvents = new ArrayList<>();
        this.containerService = new StandardContainerService(this);
        this.threadProvider = new ThreadProviderFactory(this);
        this.checks = Alpha.getInstance().getCheckFactory().loadChecks(this);
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public UUID getUniqueId() {
        return this.uuid;
    }

    @Override
    public List<AbstractCheck> getChecks() {
        return this.checks;
    }

    @Override
    public ContainerService getContainerService() {
        return this.containerService;
    }

    @Override
    public ThreadProvider getThreadProvider() {
        return this.threadProvider;
    }

    @Override
    public List<EventListener> getTrackedListeners() {
        return this.trackedEvents;
    }

    @Override
    public void keepTrackOf(EventListener listener) {
        this.trackedEvents.add(listener);
    }

    @Override
    public EntityPlayer getHandle() {
        return ((CraftPlayer) this.player).getHandle();
    }
}

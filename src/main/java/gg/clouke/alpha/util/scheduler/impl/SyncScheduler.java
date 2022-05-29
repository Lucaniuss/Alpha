package gg.clouke.alpha.util.scheduler.impl;

import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.util.scheduler.Scheduler;

/**
 * @author Clouke
 * @since 28.05.2022 19:36
 * © Alpha - All Rights Reserved
 */
public final class SyncScheduler implements Scheduler {

    private static final Alpha plugin = Alpha.getInstance();

    @Override
    public Scheduler execute(Runnable runnable) {
        plugin.getServer().getScheduler().runTask(plugin, runnable);
        return this;
    }

    @Override
    public Scheduler then(Runnable runnable) {
        plugin.getServer().getScheduler().runTask(plugin, runnable);
        return this;
    }
}

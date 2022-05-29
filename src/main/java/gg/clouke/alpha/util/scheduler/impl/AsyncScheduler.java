package gg.clouke.alpha.util.scheduler.impl;

import gg.clouke.alpha.util.scheduler.Scheduler;

import java.util.concurrent.CompletableFuture;

/**
 * @author Clouke
 * @since 28.05.2022 19:36
 * © Alpha - All Rights Reserved
 */
public final class AsyncScheduler implements Scheduler {

    @Override
    public Scheduler execute(Runnable runnable) {
        CompletableFuture.runAsync(runnable);
        return this;
    }

    @Override
    public Scheduler then(Runnable runnable) {
        CompletableFuture.runAsync(runnable);
        return this;
    }
}

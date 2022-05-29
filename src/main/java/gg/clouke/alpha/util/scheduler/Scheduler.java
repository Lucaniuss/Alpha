package gg.clouke.alpha.util.scheduler;

import gg.clouke.alpha.util.scheduler.impl.AsyncScheduler;
import gg.clouke.alpha.util.scheduler.impl.SyncScheduler;

/**
 * @author Clouke
 * @since 28.05.2022 19:22
 * © Alpha - All Rights Reserved
 */
public interface Scheduler {

    static Scheduler async() {
        return new AsyncScheduler();
    }

    static Scheduler sync() {
        return new SyncScheduler();
    }

    Scheduler execute(Runnable runnable);

    Scheduler then(Runnable runnable);

}

package gg.clouke.alpha.provider.tick.impl;

import gg.clouke.alpha.util.scheduler.AbstractScheduler;
import gg.clouke.alpha.util.scheduler.SchedulerType;
import lombok.Getter;

/**
 * @author Clouke
 * @since 29.05.2022 12:23
 * © Alpha - All Rights Reserved
 */
public class TickTimer extends AbstractScheduler {

    @Getter
    private int tick;

    public TickTimer(SchedulerType type, int defaultTick) {
        super(type, 0L, 1L);
        this.tick = defaultTick;
    }

    @Override
    public void run() {
        tick++;
    }

    public void reset() {
        tick = 0;
    }

}

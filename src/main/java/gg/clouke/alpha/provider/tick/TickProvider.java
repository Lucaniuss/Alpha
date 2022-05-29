package gg.clouke.alpha.provider.tick;

import gg.clouke.alpha.provider.tick.impl.TickTimer;
import gg.clouke.alpha.util.annotations.DoNotMock;
import gg.clouke.alpha.util.functions.Executable;
import gg.clouke.alpha.util.scheduler.SchedulerType;

/**
 * @author Clouke
 * @since 28.05.2022 19:23
 * © Alpha - All Rights Reserved
 */

@DoNotMock("Use TickProviderFactory();")
public interface TickProvider {

    static TickTimer create(SchedulerType type, int defaultTick) {
        return new TickTimer(type, defaultTick);
    }

    /**
     * Starts the tick provider.
     */
    TickProvider start();

    /**
     * Stops the tick provider.
     */
    TickProvider stop();

    /**
     * Resets the tick provider.
     */
    TickProvider reset();

    /**
     * @return The current tick(s).
     */
    int getTicks();

    /**
     * @return True if the tick provider is running.
     */
    boolean isRunning();

    /**
     * Run actions within the tick provider if running.
     */
    TickProvider ifRunning();

    /**
     * @param executable The action to be queued.
     */
    void queueAction(Executable executable);

    /**
     * @param ticks The ticks to check
     * @return True if the tick provider has passed the given ticks.
     */
    boolean hasPassed(int ticks);

    /**
     * @return {@link TickableChain} of the tick provider.
     */
    TickableChain getChain();

}

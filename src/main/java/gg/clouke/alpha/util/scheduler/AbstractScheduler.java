package gg.clouke.alpha.util.scheduler;

import gg.clouke.alpha.Alpha;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Clouke
 * @since 28.05.2022 19:16
 * © Alpha - All Rights Reserved
 */
public abstract class AbstractScheduler extends BukkitRunnable {

    protected final Alpha plugin = Alpha.getInstance();

    public AbstractScheduler(SchedulerType type, long delay, long period) {
        switch (type) {
            case REPEATING_ASYNC:
                super.runTaskTimerAsynchronously(plugin, delay, period);
                break;
            case REPEATING_SYNC:
                super.runTaskTimer(plugin, delay, period);
                break;
            case LATER_SYNC:
                super.runTaskLater(plugin, delay);
                break;
            case LATER_ASYNC:
                super.runTaskLaterAsynchronously(plugin, delay);
        }
    }

    @Override
    public abstract void run();

}

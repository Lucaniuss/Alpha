package gg.clouke.alpha.tracker;

import lombok.Getter;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Clouke
 * @since 04.02.2022 00:53
 * All Rights Reserved
 */

@Getter
public class TickTracker extends BukkitRunnable {

    private int ticks, locks;

    @Override
    public void run() {
        long preTick = ms();
        this.ticks++;
        long currTick = ms();

        if (currTick - preTick > 1) {
            this.locks++;
            System.out.println("Issued Tick lock due to faulty ticks.");
        }

    }

    private long ms() {
        return System.currentTimeMillis();
    }


}

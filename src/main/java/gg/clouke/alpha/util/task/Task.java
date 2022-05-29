package gg.clouke.alpha.util.task;

import gg.clouke.alpha.Alpha;

/**
 * @author Clouke
 * @since 04.02.2022 19:06
 * All Rights Reserved
 */

public class Task {

    private static final Alpha plugin = Alpha.getInstance();

    public static void run(Runnable runnable, long delay) {
        plugin.getServer().getScheduler().runTaskLater(plugin, runnable, delay);
    }

}

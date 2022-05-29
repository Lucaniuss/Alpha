package gg.clouke.alpha.util.task;

import gg.clouke.alpha.Alpha;

/**
 * @author Clouke
 * @since 04.02.2022 19:05
 * All Rights Reserved
 */
public class Asynchronous {

    private static final Alpha plugin = Alpha.getInstance();

    public static void run(Runnable runnable) {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, runnable);
    }

}

package gg.clouke.alpha.util.task;

import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.AlphaPlugin;

/**
 * @author Clouke
 * @since 04.02.2022 19:05
 * All Rights Reserved
 */
public class Asynchronous {

    private static final AlphaPlugin plugin = Alpha.INSTANCE.getPlugin();

    public static void run(Runnable runnable) {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, runnable);
    }

}

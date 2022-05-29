package gg.clouke.alpha.util.bukkit;

import gg.clouke.alpha.Alpha;
import org.bukkit.event.Listener;

/**
 * @author Clouke
 * @since 29.05.2022 15:36
 * © Alpha - All Rights Reserved
 */
public class AbstractListener implements Listener {

    protected final Alpha plugin;

    public AbstractListener() {
        this.plugin = Alpha.getInstance();
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

}

package gg.clouke.alpha;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * This is our main bukkit event class.
 */
public final class AlphaPlugin extends JavaPlugin {

    /**
     * Gets called on load. And we redirect it to our main class.
     */
    @Override
    public void onLoad() {
        Alpha.INSTANCE.load(this);
    }

    /**
     * Gets called on plugin enable. And we redirect it to our main class.
     */
    @Override
    public void onEnable() {
        Alpha.INSTANCE.init(this);
    }

    /**
     * Gets called on plugin disable. And we redirect it to our main class.
     */
    @Override
    public void onDisable() {
        Alpha.INSTANCE.stop(this);
    }

}

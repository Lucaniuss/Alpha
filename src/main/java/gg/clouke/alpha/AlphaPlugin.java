package gg.clouke.alpha;

import org.bukkit.plugin.java.JavaPlugin;

public final class AlphaPlugin extends JavaPlugin {

    @Override
    public void onLoad() {
        Alpha.INSTANCE.load(this);
    }

    @Override
    public void onEnable() {
        Alpha.INSTANCE.init(this);
    }

    @Override
    public void onDisable() {
        Alpha.INSTANCE.stop(this);
    }

}

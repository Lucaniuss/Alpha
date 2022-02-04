package gg.clouke.alpha;

import gg.clouke.alpha.profile.ProfileRouter;
import gg.clouke.alpha.util.command.CommandFramework;
import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.utils.server.ServerVersion;
import lombok.Getter;
import gg.clouke.alpha.check.registry.CheckRegistry;
import gg.clouke.alpha.profile.ProfileRegistry;
import gg.clouke.alpha.network.NetworkProvider;
import org.bukkit.Bukkit;

@Getter
public enum Alpha {

    INSTANCE;

    private AlphaPlugin plugin;
    private ProfileRouter profileRouter;
    private CommandFramework commandFramework;

    /**
     * This method gets called on load.
     * Its recommended to create set and load PacketEvents settings here.
     *
     * @param plugin Main plugin instance.
     */
    public void load(final AlphaPlugin plugin) {
        this.plugin = plugin;

        loadPacketEvents(plugin);
    }

    /**
     * This method gets called on plugin enable.
     * Here we can register our listeners and enable our plugin.
     *
     * @param plugin Main plugin instance.
     */
    public void init(final AlphaPlugin plugin) {
        profileRouter = new ProfileRouter();
        Bukkit.getOnlinePlayers().forEach(player -> profileRouter.add(player));
        CheckRegistry.setup();
        startPacketEvents(plugin);
        handleBukkit(plugin);
        commandFramework = new CommandFramework(plugin);
    }

    public void stop(final AlphaPlugin plugin) {
        stopPacketEvents(plugin);
        Bukkit.getScheduler().cancelTasks(plugin);

        profileRouter = null;
    }


    private void loadPacketEvents(final AlphaPlugin plugin) {
        PacketEvents.create(plugin).getSettings()
                .checkForUpdates(false)
                .backupServerVersion(ServerVersion.v_1_7_10);

        PacketEvents.get().load();
    }

    private void startPacketEvents(final AlphaPlugin plugin) {
        PacketEvents.get().init();

        PacketEvents.get().getEventManager().registerListener(new NetworkProvider());
    }

    private void stopPacketEvents(final AlphaPlugin plugin) {
        PacketEvents.get().terminate();
    }

    private void handleBukkit(final AlphaPlugin plugin) {
        plugin.saveDefaultConfig();

        plugin.getServer().getPluginManager().registerEvents(new ProfileRegistry(), plugin);
    }
}

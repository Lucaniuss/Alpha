package gg.clouke.alpha;

import gg.clouke.alpha.module.alert.Alerts;
import gg.clouke.alpha.profile.ProfileRouter;
import gg.clouke.alpha.tracker.TickTracker;
import gg.clouke.alpha.util.clazz.ClassRegistration;
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
    private TickTracker tickTracker;
    private Alerts alerts;

    public void load(final AlphaPlugin plugin) {
        this.plugin = plugin;

        PacketEvents.create(plugin).getSettings()
                .checkForUpdates(false)
                .backupServerVersion(ServerVersion.v_1_8_8);

        PacketEvents.get().load();
    }

    public void init(final AlphaPlugin plugin) {
        profileRouter = new ProfileRouter();
        startPacketEvents();
        handleBukkit(plugin);
        commandFramework = new CommandFramework(plugin);
        CheckRegistry.setup();
        registerCommands();
        (tickTracker = new TickTracker()).runTaskTimerAsynchronously(plugin, 0L, 1L);
        alerts = new Alerts();
    }

    private void registerCommands() {
        ClassRegistration.loadCommandsFromPackage(plugin, "gg.clouke.alpha.command");
    }

    public void stop(final AlphaPlugin plugin) {
        stopPacketEvents();
        Bukkit.getScheduler().cancelTasks(plugin);

        profileRouter = null;
    }

    private void startPacketEvents() {
        PacketEvents.get().init();
        PacketEvents.get().getEventManager().registerListener(new NetworkProvider());
    }

    private void stopPacketEvents() {
        PacketEvents.get().terminate();
    }

    private void handleBukkit(final AlphaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new ProfileRegistry(), plugin);
    }
}

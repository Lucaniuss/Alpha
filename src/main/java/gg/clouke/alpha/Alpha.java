package gg.clouke.alpha;

import gg.clouke.alpha.module.alert.AlertModule;
import gg.clouke.alpha.profile.ProfileRouter;
import gg.clouke.alpha.tracker.TickTracker;
import gg.clouke.alpha.util.clazz.ClassRegistration;
import gg.clouke.alpha.util.command.CommandFramework;
import gg.clouke.alpha.util.config.ConfigFile;
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
    private AlertModule alertModule;
    private ConfigFile config;
    private boolean debug;

    public void load(final AlphaPlugin plugin) {
        this.plugin = plugin;

        PacketEvents.create(plugin).getSettings()
                .checkForUpdates(false)
                .backupServerVersion(ServerVersion.v_1_8_8);

        PacketEvents.get().load();
    }

    public void init(final AlphaPlugin plugin) {
        registerConfig();
        profileRouter = new ProfileRouter();
        startPacketEvents();
        handleBukkit(plugin);
        registerCommands();
        registerModules();
    }

    private void registerConfig() {
        this.config = new ConfigFile(plugin, "config.yml");
    }

    private void registerModules() {
        CheckRegistry.setup();
        (tickTracker = new TickTracker()).runTaskTimerAsynchronously(plugin, 0L, 1L);
        alertModule = new AlertModule();
        this.debug = config.getBoolean("settings.debug");
    }

    private void registerCommands() {
        commandFramework = new CommandFramework(plugin);
        ClassRegistration.loadCommandsFromPackage(plugin, "gg.clouke.alpha.command");
    }

    public void stop(final AlphaPlugin plugin) {
        stopPacketEvents();
        Bukkit.getScheduler().cancelTasks(plugin);
        this.config.save();
        this.profileRouter = null;
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

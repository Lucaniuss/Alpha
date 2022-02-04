package gg.clouke.alpha;

import gg.clouke.alpha.profile.ProfileRouter;
import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.utils.server.ServerVersion;
import lombok.Getter;
import gg.clouke.alpha.check.manager.CheckManager;
import gg.clouke.alpha.listener.bukkit.RegistrationListener;
import gg.clouke.alpha.listener.packet.PacketListener;
import org.bukkit.Bukkit;

@Getter
public enum Alpha {

    /**
     * This is our enum instance for our AntiCheat class. You can use this to access non-static stuff in this class.
     * You may ask why enum instance and that is a great question, the answer is its cooler and cleaner than the old fashion way.
     */
    INSTANCE;

    /**
     * This is where we store our main plugin instance.
     * If we need to use it we can easily access it here.
     */
    private AlphaPlugin plugin;

    /**
     * This is where all our PlayerData is managed.
     * This gets cleared on onEnable and on onDisable.
     */
    private ProfileRouter playerDataManager;

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
        playerDataManager = new ProfileRouter();
        Bukkit.getOnlinePlayers().forEach(player -> playerDataManager.add(player));

        CheckManager.setup();

        startPacketEvents(plugin);
        handleBukkit(plugin);
    }

    /**
     * This method gets called on plugin enable.
     * We can disable our plugin and cancel all tasks here.
     *
     * @param plugin Main plugin instance.
     */
    public void stop(final AlphaPlugin plugin) {
        stopPacketEvents(plugin);
        Bukkit.getScheduler().cancelTasks(plugin);

        playerDataManager = null;
    }

    /*
    Functional programming good so we do cool stuff.
     */

    /**
     * We create and load PacketEvents.
     *
     * @param plugin Main plugin instance.
     */
    private void loadPacketEvents(final AlphaPlugin plugin) {
        PacketEvents.create(plugin).getSettings()
                .checkForUpdates(false)
                .backupServerVersion(ServerVersion.v_1_7_10);

        PacketEvents.get().load();
    }

    /**
     * Start PacketEvents and register our packet listener.
     *
     * @param plugin Main plugin instance.
     */
    private void startPacketEvents(final AlphaPlugin plugin) {
        PacketEvents.get().init();

        PacketEvents.get().getEventManager().registerListener(new PacketListener());
    }

    /**
     * Stop PacketEvents.
     *
     * @param plugin Main plugin instance.
     */
    private void stopPacketEvents(final AlphaPlugin plugin) {
        PacketEvents.get().terminate();
    }

    /**
     * This method will handle everything related to bukkit on enable.
     *
     * @param plugin Main plugin instance.
     */
    private void handleBukkit(final AlphaPlugin plugin) {
        plugin.saveDefaultConfig();

        plugin.getServer().getPluginManager().registerEvents(new RegistrationListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new BukkitEventListener(), plugin);
    }
}

package gg.clouke.alpha;

import gg.clouke.alpha.check.CheckFactory;
import gg.clouke.alpha.profile.ProfileProvider;
import gg.clouke.alpha.profile.container.standard.ContainerFactory;
import gg.clouke.alpha.profile.impl.BukkitProfileInjector;
import gg.clouke.alpha.profile.impl.StandardProfileProvider;
import gg.clouke.alpha.provider.alert.AlertProvider;
import gg.clouke.alpha.provider.alert.impl.StandardAlertProvider;
import gg.clouke.alpha.provider.event.EventBus;
import gg.clouke.alpha.provider.event.EventProvider;
import gg.clouke.alpha.provider.log.LogProvider;
import gg.clouke.alpha.provider.log.impl.StandardLogProvider;
import gg.clouke.alpha.provider.mongo.MongoProvider;
import gg.clouke.alpha.provider.mongo.impl.StandardMongoProvider;
import gg.clouke.alpha.provider.network.NetworkProvider;
import gg.clouke.alpha.provider.network.impl.PacketProvider;
import gg.clouke.alpha.provider.network.impl.StandardNetworkProvider;
import gg.clouke.alpha.provider.thread.impl.ThreadWatcher;
import gg.clouke.alpha.provider.tick.TickProvider;
import gg.clouke.alpha.provider.tick.impl.TickProviderFactory;
import gg.clouke.alpha.util.clazz.ClassService;
import gg.clouke.alpha.util.command.CommandFramework;
import gg.clouke.alpha.util.config.ConfigFile;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

@Getter
public final class Alpha extends JavaPlugin {

    @Getter private static Alpha instance;

    private PacketProvider packetProvider;
    private AlertProvider alertProvider;
    private CheckFactory checkFactory;
    private ContainerFactory containerFactory;
    private EventProvider eventProvider;
    private TickProvider tickProvider;
    private ProfileProvider profileProvider;
    private CommandFramework commandFramework;
    private NetworkProvider networkProvider;
    private ThreadWatcher threadWatcher;
    private ConfigFile config;
    private MongoProvider mongoProvider;
    private LogProvider logProvider;
    private boolean debug;

    @Override
    public void onEnable() {
        instance = this;

        this.config = new ConfigFile(this, "config.yml");
        this.containerFactory = new ContainerFactory();
        this.checkFactory = new CheckFactory();
        this.commandFramework = new CommandFramework(this);
        this.packetProvider = new PacketProvider();
        this.networkProvider = new StandardNetworkProvider();
        this.profileProvider = new StandardProfileProvider();
        this.alertProvider = new StandardAlertProvider();
        this.threadWatcher = new ThreadWatcher();
        this.tickProvider = new TickProviderFactory();
        this.eventProvider = new EventBus();
        this.mongoProvider = new StandardMongoProvider()
                .enable(config.getBoolean("MONGO.ENABLED"))
                .host(config.getString("MONGO.HOST"))
                .port(config.getInt("MONGO.PORT"))
                .database(config.getString("MONGO.DATABASE"))
                .auth(config.getBoolean("MONGO.AUTH.ENABLED"))
                .user(config.getString("MONGO.AUTH.USER"))
                .pass(config.getString("MONGO.AUTH.PASS"))
                .authDb(config.getString("MONGO.AUTH.AUTH-DB"))
                .connect();
        this.logProvider = new StandardLogProvider();

        ClassService.loadCommandsFromPackage(this, "gg.clouke.alpha.command");
        new BukkitProfileInjector();

        this.tickProvider.start();
    }

    @Override
    public void onDisable() {
        this.logProvider.save();
        Optional.ofNullable(this.mongoProvider).ifPresent(MongoProvider::dispose);
        this.tickProvider.stop();
    }

}

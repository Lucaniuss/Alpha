package gg.clouke.alpha.provider.network.impl;

import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.provider.network.NetworkProvider;
import gg.clouke.alpha.provider.network.impl.handler.PacketHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */
public class StandardNetworkProvider implements NetworkProvider {

    private static final ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public void inject(Profile profile, Player player) {
        executor.submit(() -> {
            PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;

            Channel channel = playerConnection.networkManager.channel;
            ChannelPipeline pipeline = channel.pipeline();

            pipeline.addBefore("packet_handler", "ac-handler", new PacketHandler(profile));
        });
    }

    @Override
    public void eject(Player player) {
        executor.submit(() -> {
            PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;

            if (playerConnection != null && !playerConnection.isDisconnected()) {
                Channel channel = playerConnection.networkManager.channel;
                ChannelPipeline pipeline = channel.pipeline();

                channel.eventLoop().execute(() -> {
                    if (pipeline.get("ac-handler") != null)
                        pipeline.remove("ac-handler");
                });
            }
        });
    }

}

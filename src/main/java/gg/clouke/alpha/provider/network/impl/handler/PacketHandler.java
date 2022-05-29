package gg.clouke.alpha.provider.network.impl.handler;

import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.provider.network.impl.system.EPacket;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import lombok.RequiredArgsConstructor;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketListenerPlayIn;
import net.minecraft.server.v1_8_R3.PacketListenerPlayOut;

/**
 * Credit goes to https://github.com/sim0n
 * @see <a href="https://github.com/sim0n/Nemesis/tree/main/src/main/java/dev/sim0n/anticheat/net">...</a>
 */
@RequiredArgsConstructor
public class PacketHandler extends ChannelDuplexHandler {

    private final Alpha plugin = Alpha.getInstance();
    private final Profile profile;

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        long now = System.currentTimeMillis();
        Packet<PacketListenerPlayIn> nmsPacket = (Packet<PacketListenerPlayIn>) o;
        EPacket<?> packet = plugin.getPacketProvider().handle(nmsPacket, true, now);

        super.channelRead(channelHandlerContext, o);

        try {
            profile.getThreadProvider().get().execute(() -> profile.getContainerService().handle(packet));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void write(ChannelHandlerContext channelHandlerContext, Object o, ChannelPromise channelPromise) throws Exception {
        long now = System.currentTimeMillis();
        Packet<PacketListenerPlayOut> nmsPacket = (Packet<PacketListenerPlayOut>) o;
        EPacket<?> packet = plugin.getPacketProvider().handle(nmsPacket, false, now);

        super.write(channelHandlerContext, o, channelPromise);

        try {
            profile.getThreadProvider().get().execute(() -> profile.getContainerService().handle(packet));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}

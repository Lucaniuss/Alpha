package gg.clouke.alpha.listener.packet;

import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.packet.Packet;
import gg.clouke.alpha.profile.Profile;
import io.github.retrooper.packetevents.event.PacketListenerAbstract;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.event.impl.PacketPlaySendEvent;
import io.github.retrooper.packetevents.event.priority.PacketEventPriority;
import gg.clouke.alpha.packet.engine.ReceivingPacketEngine;
import gg.clouke.alpha.packet.engine.SendingPacketEngine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class PacketListener extends PacketListenerAbstract {

    private final ExecutorService packetExecutor = Executors.newSingleThreadExecutor();

    public PacketListener() {
        super(PacketEventPriority.MONITOR);
    }

    @Override
    public void onPacketPlayReceive(final PacketPlayReceiveEvent event) {
        final Profile profile = Alpha.INSTANCE.getPlayerDataManager().get(event.getPlayer());
        if (profile == null) return;

        packetExecutor.execute(() -> ReceivingPacketEngine.process(profile, new Packet(Packet.Direction.SEND, event.getNMSPacket(), event.getPacketId())));
    }

    @Override
    public void onPacketPlaySend(final PacketPlaySendEvent event) {
        final Profile profile = Alpha.INSTANCE.getPlayerDataManager().get(event.getPlayer());
        if (profile == null) return;

        packetExecutor.execute(() -> SendingPacketEngine.process(profile, new Packet(Packet.Direction.SEND, event.getNMSPacket(), event.getPacketId())));
    }



}

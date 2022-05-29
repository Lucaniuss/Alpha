package gg.clouke.alpha.profile.container.impl;

import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.profile.container.AbstractContainer;
import gg.clouke.alpha.profile.container.PacketContainer;
import gg.clouke.alpha.provider.network.impl.system.EPacket;
import gg.clouke.alpha.provider.network.impl.system.bound.client.SPacketEntityTeleport;
import gg.clouke.alpha.provider.network.impl.system.bound.server.CPacketFlying;
import gg.clouke.alpha.provider.network.impl.system.bound.server.CPacketUseEntity;
import gg.clouke.alpha.util.functions.Condition;
import gg.clouke.alpha.util.player.Human;
import gg.clouke.alpha.util.player.StandardHuman;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Clouke
 * @since 29.05.2022 11:36
 * © Alpha - All Rights Reserved
 */

@Getter
public final class EntityContainer extends AbstractContainer implements PacketContainer {

    private final Map<Integer, Human> entities;

    public EntityContainer(Profile profile) {
        super(profile);
        this.entities = new HashMap<>();
    }

    @Override
    public void handle(EPacket<?> packet) {
        if (packet instanceof CPacketFlying) {
            profile.getContainerService().get(TransactionContainer.class).dispatch(() -> this.entities.forEach((id, human) -> {
                Profile profile = plugin.getProfileProvider().get(id);
                Condition.ofNullable(profile, () -> human.move(profile.getContainerService().get(PositionContainer.class).getClient()));
            }));
        } else if (packet instanceof SPacketEntityTeleport) {
            final SPacketEntityTeleport wrapper = (SPacketEntityTeleport) packet;
            final int id = wrapper.getEntityId();
            Human human = this.entities.get(id);
            if (human != null) {
                profile.getContainerService().get(TransactionContainer.class).dispatch(() -> {
                    Profile profile = plugin.getProfileProvider().get(id);
                    Condition.ofNullable(profile, () -> human.move(profile.getContainerService().get(PositionContainer.class).getClient()));
                });
            }
        } else if (packet instanceof CPacketUseEntity) {
            final CPacketUseEntity wrapper = (CPacketUseEntity) packet;
            final int entityId = wrapper.getEntityId();
            Condition.of(!entities.containsKey(entityId), () -> this.entities.put(entityId, new StandardHuman()));
        }
    }

}

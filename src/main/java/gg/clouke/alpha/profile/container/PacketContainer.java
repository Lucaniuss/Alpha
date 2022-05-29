package gg.clouke.alpha.profile.container;

import gg.clouke.alpha.provider.network.impl.system.EPacket;

/**
 * @author Clouke
 * @since 29.05.2022 00:02
 * © Alpha - All Rights Reserved
 */
public interface PacketContainer {

    void handle(EPacket<?> packet);

}

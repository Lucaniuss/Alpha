package gg.clouke.alpha.profile.container;

import gg.clouke.alpha.provider.network.impl.system.EPacket;

import java.util.List;

/**
 * @author Clouke
 * @since 29.05.2022 12:47
 * © Alpha - All Rights Reserved
 */
public interface ContainerService {

    /**
     * set up a new container for the profile
     */
    void create(AbstractContainer container);

    /**
     * Update checks & containers
     */
    void handle(EPacket<?> packet);

    /**
     * delete the containers
     */
    void clear();

    /**
     * @throws ClassCastException if the container is not of the type
     * @throws NullPointerException if the container was not found
     * @return the container of the given type
     */
    <T extends AbstractContainer> T get(Class<T> type) throws NullPointerException;

    /**
     * @return the list of all containers
     */
    List<AbstractContainer> getContainers();

}

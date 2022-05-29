package gg.clouke.alpha.profile.container.standard;

import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.profile.container.AbstractContainer;
import gg.clouke.alpha.profile.container.ContainerService;
import gg.clouke.alpha.profile.container.PacketContainer;
import gg.clouke.alpha.provider.network.impl.system.EPacket;

import java.util.List;

/**
 * @author Clouke
 * @since 29.05.2022 00:14
 * © Alpha - All Rights Reserved
 */

public final class StandardContainerService implements ContainerService {

    private final Profile profile;
    private final List<AbstractContainer> containers;

    public StandardContainerService(Profile profile) {
        this.profile = profile;
        this.containers = Alpha.getInstance().getContainerFactory().loadContainers(profile);
    }

    @Override
    public void create(AbstractContainer container) {
        this.containers.add(container);
    }

    @Override
    public void handle(EPacket<?> packet) {
        this.containers.stream()
                .filter(container -> container instanceof PacketContainer)
                .forEach(container -> ((PacketContainer) container).handle(packet));

        this.profile.getChecks().forEach(check -> check.handle(packet));
    }

    @Override
    public void clear() {
        this.containers.clear();
    }

    @Override @SuppressWarnings("unchecked")
    public <T extends AbstractContainer> T get(Class<T> type) throws NullPointerException {
        T result = null;
        for (AbstractContainer container : this.containers) {
            if (container.getClass().equals(type)) {
                result = (T) container;
                break;
            }
        }

        return result;
    }

    @Override
    public List<AbstractContainer> getContainers() {
        return this.containers;
    }

}

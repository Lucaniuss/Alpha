package gg.clouke.alpha.profile.container;

import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.profile.Profile;

/**
 * @author Clouke
 * @since 28.05.2022 23:51
 * © Alpha - All Rights Reserved
 */
public abstract class AbstractContainer {

    protected final Profile profile;
    protected final Alpha plugin;
    protected final int id;

    public AbstractContainer(Profile profile) {
        this.profile = profile;
        this.plugin = Alpha.getInstance();
        this.id = profile.getPlayer().getEntityId();
    }

}

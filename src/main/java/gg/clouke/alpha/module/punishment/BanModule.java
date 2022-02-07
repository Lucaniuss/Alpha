package gg.clouke.alpha.module.punishment;

import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.module.Module;
import gg.clouke.alpha.profile.Profile;

/**
 * @author Clouke
 * @since 06.02.2022 21:18
 * All Rights Reserved
 */

public class BanModule extends Module {

    public BanModule(final Profile profile) {
        super(profile);
        this.punish();
    }

    public void punish() {
        String name = profile.getPlayer().getName();
        if (profile.isBanned()) return;

        if (!Alpha.INSTANCE.isDebug()) {

            cast(" ");
            cast("&7 * &cAlpha &ehas banned &6" + name + " &efor cheating&7.");
            cast(" ");

            profile.setBanned(true);
            cons("ban " + name + " permanent Unfair Advantage -s");
        }

    }

}

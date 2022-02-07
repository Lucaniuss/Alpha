package gg.clouke.alpha.module;

import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.util.chat.CC;
import lombok.Getter;
import org.bukkit.Bukkit;

/**
 * @author Clouke
 * @since 06.02.2022 21:19
 * All Rights Reserved
 */

@Getter
public abstract class Module {

    public final Profile profile;

    public Module(Profile profile) {
        this.profile = profile;
    }

    protected void cast(String str) {
        Bukkit.broadcastMessage(CC.translate(str));
    }

    protected void cons(String str) {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), str);
    }

}

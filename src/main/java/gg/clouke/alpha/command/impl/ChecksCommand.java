package gg.clouke.alpha.command.impl;

import gg.clouke.alpha.check.BaseCheck;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.util.chat.CC;
import gg.clouke.alpha.util.command.BaseCommand;
import gg.clouke.alpha.util.command.Command;
import gg.clouke.alpha.util.command.CommandArgs;
import org.bukkit.entity.Player;

/**
 * @author Clouke
 * @since 29.05.2022 15:52
 * © Alpha - All Rights Reserved
 */
public class ChecksCommand extends BaseCommand {

    @Command(name = "alpha.checks", permission = "alpha.command.checks")
    public void onDispatch(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        Profile profile = this.plugin.getProfileProvider().get(player.getUniqueId());

        player.sendMessage(CC.translate("&7&m---------------------------------"));
        profile.getChecks().forEach(check -> {
            BaseCheck base = check.getBaseCheck();
            player.sendMessage(CC.translate("&7- &e" + base.name() + " " + base.type() + " &7(" + base.maxVl() + ")"));
        });
        player.sendMessage(CC.translate("&7&m---------------------------------"));
    }
}

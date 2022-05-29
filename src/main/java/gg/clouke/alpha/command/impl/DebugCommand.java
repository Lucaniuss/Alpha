package gg.clouke.alpha.command.impl;

import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.util.chat.CC;
import gg.clouke.alpha.util.command.BaseCommand;
import gg.clouke.alpha.util.command.Command;
import gg.clouke.alpha.util.command.CommandArgs;
import org.bukkit.entity.Player;

/**
 * @author Clouke
 * @since 04.02.2022 23:30
 * All Rights Reserved
 */

public class DebugCommand extends BaseCommand {

    @Command(name = "alpha.debug", permission = "alpha.command.debug")
    public void onDispatch(final CommandArgs cmd) {
        Player player = cmd.getPlayer();
        String[] args = cmd.getArgs();

        if (args.length < 1) {
            player.sendMessage(CC.translate("&cUsage: /alpha debug <check>"));
            return;
        }

        final String queryCheck = args[0];
        final Profile profile = plugin.getProfileProvider().get(player.getUniqueId());

        profile.getChecks()
                .stream()
                .filter(check -> check.getClass().getSimpleName().equalsIgnoreCase(queryCheck))
                .findFirst()
                .ifPresent(check -> {
                    boolean debugging = check.isDebug();
                    check.setDebug(!debugging);
                    player.sendMessage(CC.translate("&eDebugging for " + queryCheck + " " + (debugging ? "&cdisabled" : "&aenabled") + "&e."));
                });

    }
}

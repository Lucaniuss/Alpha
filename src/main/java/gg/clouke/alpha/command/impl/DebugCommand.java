package gg.clouke.alpha.command.impl;

import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.check.Check;
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
    @Override
    public void onCommand(final CommandArgs cmd) {
        Player player = cmd.getPlayer();
        String[] args = cmd.getArgs();

        if (args.length != 2) {
            player.sendMessage(CC.translate("&cUsage: /alpha debug <check> <type>"));
            return;
        }

        final String queryCheck = args[0] + " " + args[1];
        final Profile profile = Alpha.INSTANCE.getProfileRouter().get(player);
        for (final Check check : profile.getChecks()) {
            final String loopedCheck = check.getBaseCheck().name() + " " + check.getBaseCheck().type();
            if (queryCheck.equalsIgnoreCase(loopedCheck)) {
                check.setDebug(true);
                player.sendMessage(CC.translate("&eYou're now debugging &a" + loopedCheck + "&e."));
                break;
            } else {
                check.setDebug(true);
            }
        }

    }
}

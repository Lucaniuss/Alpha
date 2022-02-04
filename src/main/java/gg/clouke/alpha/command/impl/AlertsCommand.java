package gg.clouke.alpha.command.impl;

import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.util.chat.CC;
import gg.clouke.alpha.util.command.BaseCommand;
import gg.clouke.alpha.util.command.Command;
import gg.clouke.alpha.util.command.CommandArgs;
import gg.clouke.alpha.wrapper.AlertWrapper;

/**
 * @author Clouke
 * @since 04.02.2022 18:42
 * All Rights Reserved
 */

public class AlertsCommand extends BaseCommand {

    @Command(name = "alpha.alerts", permission = "alpha.command.alerts")
    @Override
    public void onCommand(final CommandArgs cmd) {
        final Profile profile = plugin.getProfileRouter().get(cmd.getPlayer());

        boolean boo = AlertWrapper.alerts.contains(profile);
        if (!boo) {
            AlertWrapper.alerts.add(profile);
            profile.getPlayer().sendMessage(CC.translate("&eYou've &aenabled &eAlpha alerts"));
            return;
        }

        AlertWrapper.alerts.remove(profile);
        profile.getPlayer().sendMessage(CC.translate("&eYou've &cdisabled &eAlpha alerts"));
    }
}

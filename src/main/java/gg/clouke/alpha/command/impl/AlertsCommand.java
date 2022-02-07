package gg.clouke.alpha.command.impl;

import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.module.alert.AlertModule;
import gg.clouke.alpha.util.command.BaseCommand;
import gg.clouke.alpha.util.command.Command;
import gg.clouke.alpha.util.command.CommandArgs;
import org.bukkit.entity.Player;

/**
 * @author Clouke
 * @since 04.02.2022 18:42
 * All Rights Reserved
 */

public class AlertsCommand extends BaseCommand {

    @Command(name = "alpha.alerts", permission = "alpha.command.alerts")
    @Override
    public void onCommand(final CommandArgs cmd) {
        final Player player = cmd.getPlayer();
        final AlertModule alertModule = Alpha.INSTANCE.getAlertModule();

        if (alertModule.hasAlerts(player)) {
            alertModule.remove(player);
            return;
        }

        alertModule.add(player);
    }
}

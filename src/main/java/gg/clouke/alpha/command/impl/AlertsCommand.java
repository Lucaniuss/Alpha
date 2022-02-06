package gg.clouke.alpha.command.impl;

import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.module.alert.Alerts;
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
        final Alerts alerts = Alpha.INSTANCE.getAlerts();

        if (alerts.hasAlerts(player)) {
            alerts.remove(player);
            return;
        }

        alerts.add(player);
    }
}

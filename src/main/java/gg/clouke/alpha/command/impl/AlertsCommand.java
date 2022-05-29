package gg.clouke.alpha.command.impl;

import gg.clouke.alpha.provider.alert.AlertProvider;
import gg.clouke.alpha.util.command.BaseCommand;
import gg.clouke.alpha.util.command.Command;
import gg.clouke.alpha.util.command.CommandArgs;
import gg.clouke.alpha.util.functions.Condition;
import org.bukkit.entity.Player;

/**
 * @author Clouke
 * @since 04.02.2022 18:42
 * All Rights Reserved
 */

public class AlertsCommand extends BaseCommand {

    @Command(name = "alpha.alerts", permission = "alpha.command.alerts")
    public void onDispatch(final CommandArgs cmd) {
        final Player player = cmd.getPlayer();
        final AlertProvider alerts = plugin.getAlertProvider();

        Condition.of(alerts.isSubscribed(player), () -> alerts.unsubscribe(player)).orElse(() -> alerts.subscribe(player));
    }
}

package gg.clouke.alpha.command.impl;

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

        if (args.length < 1) {
            player.sendMessage(CC.translate("&cUsage: /alpha debug <check> <type>"));
            return;
        }

    }
}

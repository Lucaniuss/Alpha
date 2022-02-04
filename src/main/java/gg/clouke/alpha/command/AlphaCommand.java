package gg.clouke.alpha.command;

import gg.clouke.alpha.util.chat.CC;
import gg.clouke.alpha.util.clazz.ClassRegistration;
import gg.clouke.alpha.util.command.BaseCommand;
import gg.clouke.alpha.util.command.Command;
import gg.clouke.alpha.util.command.CommandArgs;
import gg.clouke.alpha.util.task.Asynchronous;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Clouke
 * @since 04.02.2022 18:35
 * All Rights Reserved
 */

public class AlphaCommand extends BaseCommand {

    private final List<String> commands = new ArrayList<>();

    @Command(name = "alpha", permission = "alpha.command.alpha")
    @Override
    public void onCommand(final CommandArgs cmd) {
        Asynchronous.run(() -> {
            final Player player = cmd.getPlayer();

            if (commands.isEmpty()) { // Only initialize once
                ClassRegistration.getClassesInPackage(plugin.getPlugin(), "gg.clouke.command.impl")
                        .forEach(clazz -> commands.add(clazz.getSimpleName().toLowerCase()));
            }

            player.sendMessage(CC.translate("&e&lAlpha Commands (&b&l" + commands.size() + "&e&l):"));
            for (final String command : commands) {
                player.sendMessage(CC.translate(" &7* &e/" + cmd.getLabel() + " " + command));
            }

        });
    }
}

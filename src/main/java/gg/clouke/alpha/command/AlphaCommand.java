package gg.clouke.alpha.command;

import gg.clouke.alpha.util.chat.CC;
import gg.clouke.alpha.util.clazz.ClassService;
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

    private static final List<String> commands = new ArrayList<>();

    @Command(name = "alpha", permission = "alpha.command.alpha")
    public void onDispatch(final CommandArgs cmd) {
        Asynchronous.run(() -> {
            final Player player = cmd.getPlayer();

            if (commands.isEmpty()) { // Only initialize once
                ClassService.getClasses(plugin, "gg.clouke.alpha.command.impl")
                        .forEach(clazz -> commands.add(clazz.getSimpleName().toLowerCase().replace("command", "")));
            }

            player.sendMessage(CC.translate("&e&lAlpha Commands (&a&l" + commands.size() + "&e&l):"));
            commands.forEach(command -> player.sendMessage(CC.translate("&8&l- &e/alpha &7" + command)));
        });
    }
}

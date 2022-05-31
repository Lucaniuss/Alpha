package gg.clouke.alpha.command;

import gg.clouke.alpha.util.chat.CC;
import gg.clouke.alpha.util.clazz.ClassService;
import gg.clouke.alpha.util.command.BaseCommand;
import gg.clouke.alpha.util.command.Command;
import gg.clouke.alpha.util.command.CommandArgs;
import gg.clouke.alpha.util.command.Completer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Clouke
 * @since 04.02.2022 18:35
 * All Rights Reserved
 */
public class AlphaCommand extends BaseCommand {

    private final List<String> commands;

    public AlphaCommand() {
        commands = ClassService.getClasses(plugin, "gg.clouke.alpha.command.impl")
                .stream()
                .map(clazz -> clazz.getSimpleName().replace("Command", "").toLowerCase())
                .collect(Collectors.toList());
    }

    @Command(name = "alpha", permission = "alpha.command.alpha")
    public void onDispatch(final CommandArgs cmd) {
        Player player = cmd.getPlayer();

        player.sendMessage(CC.translate("&e&lAlpha Commands (&a&l" + commands.size() + "&e&l):"));
        commands.forEach(command -> player.sendMessage(CC.translate("&7- &e/alpha &7" + command)));
    }

    @Completer(name = "alpha")
    public List<String> onCompleter(CommandArgs cmd) {
        return commands;
    }
}

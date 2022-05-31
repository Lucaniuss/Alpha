package gg.clouke.alpha.command.impl;

import gg.clouke.alpha.provider.log.Log;
import gg.clouke.alpha.util.chat.CC;
import gg.clouke.alpha.util.command.BaseCommand;
import gg.clouke.alpha.util.command.Command;
import gg.clouke.alpha.util.command.CommandArgs;
import gg.clouke.alpha.util.command.Completer;
import gg.clouke.alpha.util.task.Asynchronous;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Lucanius
 * @since May 31, 2022.
 * Alpha - All Rights Reserved.
 */
public class LogsCommand extends BaseCommand {

    @Command(name = "alpha.logs", permission = "alpha.command.logs")
    public void onDispatch(CommandArgs cmd) {
        Asynchronous.run(() -> {
            Player player = cmd.getPlayer();
            String[] args = cmd.getArgs();

            if (args.length != 1) {
                player.sendMessage(CC.translate("&cUsage: /alpha logs <user>"));
                return;
            }

            Player target = plugin.getServer().getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(CC.translate("&cPlayer not found!"));
                return;
            }

            Set<Log> logs = plugin.getLogProvider().fetch(target.getUniqueId());
            if (logs.isEmpty()) {
                player.sendMessage(CC.translate("&cNo logs found!"));
                return;
            }

            StringBuilder builder = new StringBuilder();
            boolean shouldPaste = logs.size() > 15;
            logs.forEach(log ->
                    builder.append(shouldPaste ? target.getName() + " -> " : "")
                    .append(shouldPaste ? "" : " &e")
                    .append("[")
                    .append(log.getBaseName())
                    .append(" ")
                    .append(log.getBaseType())
                    .append("]")
                    .append(shouldPaste ? "" : "&7")
                    .append(" ")
                    .append(log.getData())
                    .append("\n"));

            if (shouldPaste) {
                player.sendMessage(CC.translate("&eUploaded logs to: &7https://pastie.io/" + plugin.getLogProvider().toPaste(builder.toString())));
                return;
            }

            player.sendMessage(CC.translate("&e" + target.getName() + "'s Logs:"));
            player.sendMessage(CC.translate(builder.toString()));
        });
    }

    @Completer(name = "alpha.logs")
    public List<String> onCompleter(CommandArgs cmd) {
        return plugin.getServer().getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
    }
}

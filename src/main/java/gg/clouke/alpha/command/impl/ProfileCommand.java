package gg.clouke.alpha.command.impl;

import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.profile.container.ContainerService;
import gg.clouke.alpha.profile.container.impl.TransactionContainer;
import gg.clouke.alpha.util.chat.CC;
import gg.clouke.alpha.util.command.BaseCommand;
import gg.clouke.alpha.util.command.Command;
import gg.clouke.alpha.util.command.CommandArgs;
import org.bukkit.entity.Player;

/**
 * @author Clouke
 * @since 29.05.2022 16:37
 * © Alpha - All Rights Reserved
 */
public class ProfileCommand extends BaseCommand {

    @Command(name = "alpha.profile", permission = "alpha.command.profile")
    public void onDispatch(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        String[] args = cmd.getArgs();

        if (args.length < 1) {
            player.sendMessage(CC.translate("&cUsage: /alpha profile <user>"));
            return;
        }

        Player target = plugin.getServer().getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(CC.translate("&cPlayer not found!"));
            return;
        }

        Profile profile = plugin.getProfileProvider().get(target.getUniqueId());
        ContainerService container = profile.getContainerService();
        TransactionContainer transactions = container.get(TransactionContainer.class);
        player.sendMessage(CC.translate("&e" + target.getName() + "'s Alpha Profile:"));
        player.sendMessage(CC.translate("&7- &eInjected Checks: &f" + profile.getChecks().size()));
        player.sendMessage(CC.translate("&7- &ePackets/S: &f" + transactions.getInterval()));
        player.sendMessage(CC.translate("&7- &ePackets/M: &f" + transactions.getInterval() * 60));
        player.sendMessage(CC.translate("&7- &eThread-Index: &f" + profile.getThreadProvider().getGroup()));
    }
}

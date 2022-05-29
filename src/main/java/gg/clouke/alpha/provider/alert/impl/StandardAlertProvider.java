package gg.clouke.alpha.provider.alert.impl;

import com.google.common.base.Preconditions;
import gg.clouke.alpha.check.AbstractCheck;
import gg.clouke.alpha.check.BaseCheck;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.provider.alert.AlertProvider;
import gg.clouke.alpha.provider.event.events.AlphaFlagEvent;
import gg.clouke.alpha.util.chat.CC;
import gg.clouke.alpha.util.chat.Clickable;
import gg.clouke.alpha.util.functions.Condition;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Clouke
 * @since 06.02.2022 17:04
 * All Rights Reserved
 */

public class StandardAlertProvider implements AlertProvider {

    private final Set<Player> alerts;
    private final ExecutorService thread;

    public StandardAlertProvider() {
        this.alerts = new HashSet<>();
        this.thread = Executors.newSingleThreadExecutor();
    }

    @Override
    public void flag(Profile profile, AbstractCheck check, String data) {
        thread.execute(() -> {
            AlphaFlagEvent event = new AlphaFlagEvent(profile, check, data);
            if (event.isCancelled())
                return;

            BaseCheck base = check.getBaseCheck();
            Condition.of(!alerts.isEmpty(), () -> {
                String player = profile.getPlayer().getName();
                String alert = CC.translate("&e[Alpha] &7" + player + " -> &e[" + base.name() + " " + base.type() + "] &7" + data);

                Clickable.builder()
                        .append(alert, "", "/tp " + player)
                        .send(alerts.iterator());
            });

            if (check.getVl() >= base.maxVl()) {
                //Scheduler.sync().execute(() -> profile.getPlayer().kickPlayer(CC.translate("&cyikes")));
            }
        });
    }

    @Override
    public void subscribe(Player player) {
        Preconditions.checkState(!this.alerts.contains(player), "Player is already subscribed.");
        this.alerts.add(player);
        player.sendMessage(CC.translate("&eYou've &aenabled &eAlpha alerts."));
    }

    @Override
    public void unsubscribe(Player player) {
        this.alerts.remove(player);
        player.sendMessage(CC.translate("&eYou've &cdisabled &eAlpha alerts."));
    }

    @Override
    public boolean isSubscribed(Player player) {
        return this.alerts.contains(player);
    }

    @Override
    public Set<Player> getSubscribers() {
        return this.alerts;
    }

    @Override
    public ExecutorService getExecutor() {
        return this.thread;
    }

}

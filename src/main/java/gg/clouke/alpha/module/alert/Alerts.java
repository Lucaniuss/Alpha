package gg.clouke.alpha.module.alert;

import gg.clouke.alpha.util.chat.CC;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Clouke
 * @since 06.02.2022 17:04
 * All Rights Reserved
 */

public class Alerts {

    private final Set<Player> alerts = new HashSet<>();

    public void add(Player player) {
        alerts.add(player);
        player.sendMessage(CC.translate("&eYou've &aenabled &eAlpha alerts."));
    }

    public void remove(Player player) {
        alerts.remove(player);
        player.sendMessage(CC.translate("&eYou've &cdisabled &eAlpha alerts."));
    }

    public boolean hasAlerts(Player player) {
        return alerts.contains(player);
    }

    public List<Player> getAlerter() {
        return new ArrayList<>(alerts);
    }

}

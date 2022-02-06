package gg.clouke.alpha.module.alert;

import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.check.Check;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.util.chat.CC;
import gg.clouke.alpha.util.chat.Clickable;
import lombok.Data;

/**
 * @author Clouke
 * @since 04.02.2022 19:09
 * All Rights Reserved
 */

@Data
public class AlertWrapper {

    private final Alerts alerts = Alpha.INSTANCE.getAlerts();

    private final Check check;
    private final Profile profile;
    private final Object data;

    public AlertWrapper(final Check check, final Profile profile, final Object data) {
        this.check = check;
        this.profile = profile;
        this.data = data;

        this.handle();
    }

    private void handle() {
        final Clickable clickable = new Clickable();
        clickable.add(CC.translate(
                "&c[Alpha] &e" + profile.getPlayer().getName() + " &7" +  check.getBaseCheck().name() + " [" + check.getBaseCheck().type() + "] (" + check.getVl() + ") &f" + data),
                CC.translate("&eData&7: &f" + data),
                "/teleport " + profile.getPlayer().getName()
        );

        alerts.getAlerter().forEach(clickable::sendToPlayer);
    }

}

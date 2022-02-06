package gg.clouke.alpha.check.impl.combat.autoclicker;

import gg.clouke.alpha.check.Check;
import gg.clouke.alpha.check.BaseCheck;
import gg.clouke.alpha.packet.Packet;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.tracker.ClickTracker;

/**
 * @author Clouke
 * @since 04.02.2022 18:53
 * All Rights Reserved
 */

@BaseCheck(name = "AutoClicker", type = "A", experimental = true)
public class AutoClickerA extends Check {

    private final ClickTracker tracker;

    public AutoClickerA(final Profile profile) {
        super(profile);
        this.tracker = profile.getClickTracker();
    }

    @Override
    public void handle(final Packet packet) {
        if (!packet.isArmAnimation()) return;
        if (!tracker.isFull()) return;

        if (tracker.getAverageTicks() <= 1.3) {
            if (++buffer >= 20) {
                buffer = 20;
                alert(tracker.getAverageTicks());
            }
        } else {
            buffer = 0;
        }
    }
}

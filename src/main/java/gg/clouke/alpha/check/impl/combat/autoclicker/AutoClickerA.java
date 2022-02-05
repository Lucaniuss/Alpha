package gg.clouke.alpha.check.impl.combat.autoclicker;

import gg.clouke.alpha.check.Check;
import gg.clouke.alpha.check.BaseCheck;
import gg.clouke.alpha.packet.Packet;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.util.helper.MathHelper;
import gg.clouke.alpha.util.list.EvictingList;

/**
 * @author Clouke
 * @since 04.02.2022 18:53
 * All Rights Reserved
 */

@BaseCheck(name = "AutoClicker", type = "A", experimental = true)
public class AutoClickerA extends Check {

    private double tick;
    private final EvictingList<Double> tickMonitor = new EvictingList<>(40);

    public AutoClickerA(final Profile profile) {
        super(profile);
    }

    @Override
    public void handle(final Packet packet) {
        if (packet.isBlockDig()) return;

        if (packet.isArmAnimation()) {
            this.tickMonitor.add(tick);
            double avg = MathHelper.getAverage(tickMonitor);

            if (!tickMonitor.isFull()) return;

            //debug(avg);
            if (avg <= 1.3) {
                alert("A: " + avg);
            }

        } else if (packet.isFlying()) {
            tick++;
        }
    }
}

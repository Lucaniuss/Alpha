package gg.clouke.alpha.check.impl.combat.autoclicker;

import gg.clouke.alpha.check.BaseCheck;
import gg.clouke.alpha.check.Check;
import gg.clouke.alpha.network.packet.Packet;
import gg.clouke.alpha.profile.Profile;

/**
 * @author Clouke
 * @since 06.02.2022 16:21
 * All Rights Reserved
 */

@BaseCheck(name = "AutoClicker", type = "B", experimental = true, maxVl = 100)
public class AutoClickerB extends Check {

    private int ticks;

    public AutoClickerB(final Profile profile) {
        super(profile);
    }

    @Override
    public void handle(final Packet packet) {
        if (packet.isFlying())  {
            this.ticks++;
        } else if (packet.isArmAnimation()) {
            int swings = 0;

            for (int i = 0; i < this.ticks; i++) {
                swings = (swings / ticks) + 1;
            }

            if (swings > 18) {
                if (++buffer > 5) {
                    alert(swings);
                }
            } else {
                buffer = 0;
            }

            this.ticks = 0;
        }
    }
}


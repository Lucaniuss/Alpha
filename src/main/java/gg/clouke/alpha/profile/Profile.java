package gg.clouke.alpha.profile;

import gg.clouke.alpha.check.Check;
import gg.clouke.alpha.tracker.ClickTracker;
import gg.clouke.alpha.tracker.CombatTracker;
import gg.clouke.alpha.util.task.Task;
import io.github.retrooper.packetevents.PacketEvents;
import lombok.Data;
import gg.clouke.alpha.check.registry.CheckRegistry;
import gg.clouke.alpha.tracker.PositionTracker;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/**
 * @author Clouke
 * @since 04.02.2022 16:45
 * All Rights Reserved
 */

@Data
public final class Profile {

    private final Player player;
    private final UUID uuid;

    private final List<Check> checks = CheckRegistry.loadChecks(this);

    private final PositionTracker positionTracker = new PositionTracker(this);
    private final CombatTracker combatTracker = new CombatTracker(this);
    private final ClickTracker clickTracker = new ClickTracker(this);

    private boolean exempted = false;

    public Profile(final Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
    }

    public void exempt(int seconds) {
        this.exempted = true;
        Task.run(() -> this.exempted = false, 20L * seconds);
    }

    public int getPing() {
        return PacketEvents.get().getPlayerUtils().getPing(this.player);
    }

    public int getPingTicks() {
        return (this.getPing() / 50) + 5;
    }

}

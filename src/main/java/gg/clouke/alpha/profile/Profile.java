package gg.clouke.alpha.profile;

import gg.clouke.alpha.check.Check;
import lombok.Data;
import gg.clouke.alpha.check.manager.CheckManager;
import gg.clouke.alpha.tracker.PositionProcessor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

@Data
public final class Profile {
    private final Player player;
    private final UUID uuid;

    private final List<Check> checks = CheckManager.loadChecks(this);
    private final PositionProcessor positionProcessor = new PositionProcessor(this);

    public Profile(final Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
    }
}

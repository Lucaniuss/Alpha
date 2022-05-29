package gg.clouke.alpha.provider.event.events;

import gg.clouke.alpha.check.AbstractCheck;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.provider.event.AbstractEvent;
import gg.clouke.alpha.provider.event.Cancellable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Clouke
 * @since 28.05.2022 23:03
 * © Alpha - All Rights Reserved
 */

@Getter @RequiredArgsConstructor
public class AlphaFlagEvent extends AbstractEvent implements Cancellable {

    private final Profile profile;
    private final AbstractCheck check;
    private final String data;

    private boolean cancelled;

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }
}

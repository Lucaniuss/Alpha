package gg.clouke.alpha.provider.event;

import gg.clouke.alpha.Alpha;
import lombok.Getter;

/**
 * @author Clouke
 * @since 28.05.2022 22:55
 * © Alpha - All Rights Reserved
 */

@Getter
public abstract class AbstractEvent implements AlphaEvent {

    private final Alpha plugin = Alpha.getInstance();

    public AbstractEvent() {
        this.plugin.getEventProvider().publish(this);
    }


}

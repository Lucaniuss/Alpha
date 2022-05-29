package gg.clouke.alpha.provider.event;

import gg.clouke.alpha.Alpha;

/**
 * @author Clouke
 * @since 28.05.2022 22:57
 * © Alpha - All Rights Reserved
 */
public interface EventListener {

    default void onEvent(AlphaEvent event) {}

    default <T extends AlphaEvent> void onEvent(Class<T> event) {}

    default void dispose() {
        Alpha.getInstance().getEventProvider().unsubscribe(this);
    }

}

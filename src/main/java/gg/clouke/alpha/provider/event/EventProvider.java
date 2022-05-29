package gg.clouke.alpha.provider.event;

import gg.clouke.alpha.Alpha;

/**
 * @author Clouke
 * @since 28.05.2022 22:55
 * © Alpha - All Rights Reserved
 */

public interface EventProvider {

    static EventProvider get() {
        return Alpha.getInstance().getEventProvider();
    }

    EventProvider subscribe(EventListener listener);

    EventProvider unsubscribe(EventListener listener);

    void publish(AlphaEvent event);

    <T extends AbstractEvent> void publish(Class<T> event);

}

package gg.clouke.alpha.provider.event.events.tick;

import gg.clouke.alpha.provider.event.AbstractEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Clouke
 * @since 28.05.2022 23:05
 * © Alpha - All Rights Reserved
 */

@Getter @RequiredArgsConstructor
public class TickStartEvent extends AbstractEvent {

    private final int ticks;

}
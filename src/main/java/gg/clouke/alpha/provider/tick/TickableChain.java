package gg.clouke.alpha.provider.tick;

import gg.clouke.alpha.util.annotations.DoNotMock;

/**
 * @author Clouke
 * @since 28.05.2022 19:50
 * © Alpha - All Rights Reserved
 */

@DoNotMock("Use TickableChainFactory();")
public interface TickableChain {

    /**
     * Subscribes a tickable to the tickable handler.
     * @param tickable {@link Tickable} to be subscribed
     */
    TickableChain subscribe(Tickable tickable);

    /**
     * Unsubscribes a tickable to the tickable handler.
     * @param tickable {@link Tickable} to be unsubscribed
     */
    TickableChain unsubscribe(Tickable tickable);

    /**
     * @param index index of the tickable to be returned
     * @return {@link Tickable} at the given index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    Tickable getTickable(int index) throws IndexOutOfBoundsException;

    /**
     * Handles each tickable in the tickable chain.
     */
    void fire();

}

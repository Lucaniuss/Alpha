package gg.clouke.alpha.provider.tick.impl;

import gg.clouke.alpha.provider.tick.Tickable;
import gg.clouke.alpha.provider.tick.TickableChain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Clouke
 * @since 28.05.2022 19:50
 * © Alpha - All Rights Reserved
 */
public class TickableChainFactory implements TickableChain {

    private final List<Tickable> tickables;

    public TickableChainFactory() {
        this.tickables = new ArrayList<>();
    }

    @Override
    public TickableChain subscribe(Tickable tickable) {
        this.tickables.add(tickable);
        return this;
    }

    @Override
    public TickableChain unsubscribe(Tickable tickable) {
        this.tickables.remove(tickable);
        return this;
    }

    @Override
    public Tickable getTickable(int index) throws IndexOutOfBoundsException {
        return this.tickables.get(index);
    }

    @Override
    public void fire() {
        this.tickables.forEach(Tickable::tick);
    }
}

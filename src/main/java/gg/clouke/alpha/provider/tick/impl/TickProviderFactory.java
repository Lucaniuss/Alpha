package gg.clouke.alpha.provider.tick.impl;

import com.google.common.base.Preconditions;
import gg.clouke.alpha.provider.event.events.tick.TickEndEvent;
import gg.clouke.alpha.provider.event.events.tick.TickStartEvent;
import gg.clouke.alpha.provider.tick.TickProvider;
import gg.clouke.alpha.provider.tick.TickableChain;
import gg.clouke.alpha.util.functions.Executable;
import gg.clouke.alpha.util.scheduler.AbstractScheduler;
import gg.clouke.alpha.util.scheduler.SchedulerType;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author Clouke
 * @since 28.05.2022 19:14
 * © Alpha - All Rights Reserved
 */
public class TickProviderFactory extends AbstractScheduler implements TickProvider {

    private final TickableChain chain;
    private final Queue<Executable> actions;

    private int ticks;
    private boolean running;

    public TickProviderFactory() {
        super(SchedulerType.REPEATING_SYNC, 0L, 1L);
        this.chain = new TickableChainFactory();
        this.actions = new ConcurrentLinkedDeque<>();
    }

    @Override
    public void run() {
        if (running) {
            ticks++;
            new TickStartEvent(ticks);

            chain.fire();

            while (!actions.isEmpty()) {
                actions.poll().execute();
            }

            new TickEndEvent(ticks);
        }
    }

    @Override
    public TickProvider start() {
        Preconditions.checkState(!this.running, "Cannot start a running tick provider");
        running = true;
        return this;
    }

    @Override
    public TickProvider stop() {
        Preconditions.checkState(this.running, "Cannot stop a stopped tick provider");
        running = false;
        ticks = 0;
        super.cancel();
        return this;
    }

    @Override
    public TickProvider reset() {
        Preconditions.checkState(this.running, "Cannot reset a stopped tick provider");
        ticks = 0;
        return this;
    }

    @Override
    public int getTicks() {
        return this.ticks;
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public TickProvider ifRunning() {
        return running ? this : null;
    }

    @Override
    public void queueAction(Executable executable) {
        this.actions.add(executable);
    }

    @Override
    public boolean hasPassed(int ticks) {
        return this.ticks >= ticks;
    }

    @Override
    public TickableChain getChain() {
        return this.chain;
    }

}

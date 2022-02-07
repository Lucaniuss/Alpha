package gg.clouke.alpha.tracker;

import gg.clouke.alpha.network.packet.Packet;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.util.helper.MathHelper;
import gg.clouke.alpha.util.list.EvictingList;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Clouke
 * @since 05.02.2022 13:32
 * All Rights Reserved
 */

@Getter
public class ClickTracker {

    private final Profile profile;
    private final EvictingList<Double> tickMonitor;
    private final List<Long> mappedCPS;
    private final List<ClickType> clickTypes;

    private ClickType clickType;
    private double averageTicks;
    private double tick;
    private boolean digging;
    private long lastClickMillis;
    private double lastClickTick;
    private int cps, lastCps;

    public ClickTracker(final Profile profile) {
        this.profile = profile;
        this.tickMonitor = new EvictingList<>(40);
        this.clickType = ClickType.NONE;
        this.clickTypes = new ArrayList<>(10);
        this.mappedCPS = new ArrayList<>(30);
    }

    public void update(final Packet packet) {
        this.digging = packet.isBlockDig();

        if (digging)
            return;

        if (packet.isFlying()) {
            tick++;
        }
        else if (packet.isArmAnimation()) {
            this.tickMonitor.add(tick);
            this.averageTicks = MathHelper.getAverage(tickMonitor);

            this.calculateCps();
            this.processClicker();
            this.mappedCPS.add(System.currentTimeMillis());

            this.lastClickMillis = System.currentTimeMillis();
            this.lastClickTick = tickMonitor.get(0);

            this.tick = 0;
        }
    }

    public boolean isFull() {
        return this.tickMonitor.isFull();
    }

    public enum ClickType {
        NONE("Not Detected"),
        JITTER("Jitter Clicking"),
        DOUBLE("Double Clicking"),
        AUTO("Auto Clicker");

        @Getter
        private final String name;

        ClickType(String name) {
            this.name = name;
        }
    }

    private void calculateCps() {
        this.lastCps = cps;

        long time = System.currentTimeMillis();
        this.mappedCPS.removeIf(i -> (i + 1000L < time));
        this.cps = this.mappedCPS.size();
    }

    private void processClicker() {
        if (this.clickType == ClickType.NONE)
            this.processDoubleClicker();
        if (this.clickType != ClickType.AUTO)
            this.processAutoClicker();
        if (this.clickType != ClickType.DOUBLE)
            this.processJitter();
    }

    /**
     * <p>
     * Dividing swings in ticks, providing us with a result of 3 ticks of a swing or more if they are double-clicking
     */
    private int _swings = 0;
    private void processDoubleClicker() {
        this._swings++;

        for (int i = 0; i < tick; i++) {
            _swings = (int) (_swings / tick);
        }

        if (_swings >= 3) {
            this.clickType = ClickType.DOUBLE;
            this.clickTypes.add(clickType);
        }
    }

    private void processAutoClicker() {
        if (!this.isFull()) return;

        double avg = this.getAverageTicks();
        if (avg <= 1.3D) { // Can go higher but to be sure we check 1.3
            this.clickType = ClickType.AUTO;
            this.clickTypes.add(clickType);
        }
    }

    private void processJitter() {
        this._swings++;

        if (this.cps <= 14) {

            for (int i = 0; i < tick; i++) {
                _swings = (int) (_swings / tick);
            }

            if (_swings < 2) {
                this.clickType = ClickType.JITTER;
                this.clickTypes.add(clickType);
            }

        }
    }
}

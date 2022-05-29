package gg.clouke.alpha.provider.thread.impl;

import gg.clouke.alpha.profile.Profile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Clouke
 * @since 28.05.2022 21:54
 * © Alpha - All Rights Reserved
 */

public class ThreadWatcher {

    private short indexWatcher = 0;
    private final Map<Profile, Short> index;

    public ThreadWatcher() {
        this.index = new HashMap<>();
    }

    public void add(Profile profile) {
        this.index.put(profile, indexWatcher++);
    }

    public short getIndex(Profile profile) {
        return this.index.get(profile);
    }

    public int size() {
        return this.index.size();
    }

    public void remove(Profile profile) {
        this.index.remove(profile);
    }

}

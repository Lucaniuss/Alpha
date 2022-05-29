package gg.clouke.alpha.provider.thread.impl;

import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.provider.thread.ThreadProvider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Clouke
 * @since 28.05.2022 21:49
 * © Alpha - All Rights Reserved
 */
public class ThreadProviderFactory implements ThreadProvider {

    private final Alpha plugin = Alpha.getInstance();

    private final Profile profile;
    private final ExecutorService thread;

    public ThreadProviderFactory(Profile profile) {
        this.profile = profile;
        this.thread = Executors.newSingleThreadExecutor();
        this.plugin.getThreadWatcher().add(profile);
    }

    @Override
    public ExecutorService get() {
        return this.thread;
    }

    @Override
    public int getGroup() {
        return this.plugin.getThreadWatcher().getIndex(profile);
    }
}

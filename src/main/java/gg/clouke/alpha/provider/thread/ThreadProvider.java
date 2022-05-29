package gg.clouke.alpha.provider.thread;

import java.util.concurrent.ExecutorService;

/**
 * @author Clouke
 * @since 28.05.2022 21:49
 * © Alpha - All Rights Reserved
 */
public interface ThreadProvider {

    ExecutorService get();

    int getGroup();

}

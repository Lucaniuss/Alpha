package gg.clouke.alpha.provider.event;

/**
 * @author Clouke
 * @since 28.05.2022 23:21
 * © Alpha - All Rights Reserved
 */
public interface Cancellable {

    void setCancelled(boolean cancelled);

    boolean isCancelled();

}

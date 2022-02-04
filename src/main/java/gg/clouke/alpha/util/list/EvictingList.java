package gg.clouke.alpha.util.list;

import lombok.Getter;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Clouke
 * @since 04.02.2022 18:54
 * All Rights Reserved
 */

public final class EvictingList<T> extends LinkedList<T> {

    @Getter
    private final int maxSize;

    public EvictingList(int maxSize) {
        this.maxSize = maxSize;
    }

    public EvictingList(Collection<? extends T> c, int maxSize) {
        super(c);
        this.maxSize = maxSize;
    }

    @Override
    public boolean add(T t) {
        if (size() >= getMaxSize()) removeFirst();
        return super.add(t);
    }

    public boolean isFull() {
        return size() >= getMaxSize();
    }
}

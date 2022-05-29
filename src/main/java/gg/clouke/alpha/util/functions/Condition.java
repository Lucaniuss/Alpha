package gg.clouke.alpha.util.functions;

import lombok.RequiredArgsConstructor;

/**
 * @author Clouke
 * @author Lucanius
 * @since May 03, 2022
 */
@RequiredArgsConstructor
public class Condition {

    private final boolean condition;

    public static Condition of(final boolean condition, final Executable runnable) {
        final Condition checker = new Condition(condition);
        if (condition) {
            runnable.execute();
        }

        return checker;
    }

    public static <T> Condition ofNullable(T type, final Executable runnable) {
        final Condition checker = new Condition(type != null);
        if (type != null) {
            runnable.execute();
        }

        return checker;
    }

    public Condition orElse(final Executable runnable) {
        if (!condition) {
            runnable.execute();
        }

        return this;
    }

    public <E> E orElseGet(final E element) {
        if (!condition) {
            return element;
        }

        return null;
    }

    public Condition orElseThrow(final Throwable throwable) throws Throwable {
        if (!condition) {
            throw throwable;
        }

        return this;
    }

}
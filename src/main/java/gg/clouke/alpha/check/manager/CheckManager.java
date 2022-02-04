package gg.clouke.alpha.check.manager;

import gg.clouke.alpha.check.Check;
import gg.clouke.alpha.check.impl.combat.range.RangeA;
import gg.clouke.alpha.profile.Profile;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public final class CheckManager {

    public final Class<?>[] CHECKS = new Class[]{
            RangeA.class,
    };

    private final List<Constructor<?>> CONSTRUCTORS = new ArrayList<>();

    public List<Check> loadChecks(final Profile data) {
        final List<Check> checkList = new ArrayList<>();
        for (final Constructor<?> constructor : CONSTRUCTORS) {
            try {
                checkList.add((Check) constructor.newInstance(data));
            } catch (final Exception exception) {
                System.err.println("Failed to load checks for " + data.getPlayer().getName());
                exception.printStackTrace();
            }
        }
        return checkList;
    }

    public void setup() {
        for (final Class<?> clazz : CHECKS) {
            try {
                CONSTRUCTORS.add(clazz.getConstructor(Profile.class));
            } catch (final NoSuchMethodException exception) {
                exception.printStackTrace();
            }
        }
    }
}

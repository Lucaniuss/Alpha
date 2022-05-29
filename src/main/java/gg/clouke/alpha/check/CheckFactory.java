package gg.clouke.alpha.check;

import com.google.common.collect.Lists;
import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.util.clazz.ClassService;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class CheckFactory {

    private static final List<Constructor<?>> CHECKS = new ArrayList<>();;

    public CheckFactory() {
        final Collection<Class<?>> classes = ClassService.getClasses(
                Alpha.getInstance(), "gg.clouke.alpha.check.impl"
        );

        classes.forEach(klass -> {
            try {
                CHECKS.add(klass.getConstructor(Profile.class));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        });

    }

    public List<AbstractCheck> loadChecks(final Profile profile) {
        final List<AbstractCheck> checks = Lists.newArrayList();
        CHECKS.forEach(check -> {
            try {
                checks.add((AbstractCheck) check.newInstance(profile));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return checks;
    }
}

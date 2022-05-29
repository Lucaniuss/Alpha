package gg.clouke.alpha.profile.container.standard;

import com.google.common.collect.Lists;
import gg.clouke.alpha.Alpha;
import gg.clouke.alpha.profile.Profile;
import gg.clouke.alpha.profile.container.AbstractContainer;
import gg.clouke.alpha.util.clazz.ClassService;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Clouke
 * @since 29.05.2022 12:06
 * © Alpha - All Rights Reserved
 */
public final class ContainerFactory {

    private static final List<Constructor<?>> CONTAINERS = new ArrayList<>();;

    public ContainerFactory() {
        final Collection<Class<?>> classes = ClassService.getClasses(
                Alpha.getInstance(), "gg.clouke.alpha.profile.container.impl"
        );

        classes.forEach(klass -> {
            try {
                CONTAINERS.add(klass.getConstructor(Profile.class));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        });

    }

    public List<AbstractContainer> loadContainers(final Profile profile) {
        final List<AbstractContainer> containers = Lists.newArrayList();
        CONTAINERS.forEach(container -> {
            try {
                containers.add((AbstractContainer) container.newInstance(profile));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return containers;
    }
}


package gg.clouke.alpha.check;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BaseCheck {

    String name();
    String type();
    boolean experimental() default false;
    int maxVl() default 20;
    boolean disabled() default false;

}

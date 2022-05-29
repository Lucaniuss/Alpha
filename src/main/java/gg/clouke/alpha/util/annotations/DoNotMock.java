package gg.clouke.alpha.util.annotations;

import java.lang.annotation.*;

/**
 * Annotation representing a type that should not be mocked.
 *
 * <p>When marking a type {@code @DoNotMock}, you should always point to alternative testing
 * solutions such as standard fakes or other testing utilities.
 *
 * <p>Mockito tests can enforce this annotation by using a custom MockMaker which intercepts
 * creation of mocks.
 *
 * @see <a href="https://github.com/google/error-prone/blob/master/annotations/src/main/java/com/google/errorprone/annotations/DoNotMock.java">...</a>
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface DoNotMock {
    /**
     * The reason why the annotated type should not be mocked.
     *
     * <p>This should suggest alternative APIs to use for testing objects of this type.
     */
    String value() default "Create a real instance instead";
}

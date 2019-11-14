package java.lang;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;

@AnnotatedFor({ "nullness" })
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, MODULE, PARAMETER, TYPE })
public @interface Deprecated {

    String since() default "";

    boolean forRemoval() default false;
}

package java.lang;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;

@AnnotatedFor({ "nullness" })
@Target({ TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE })
@Retention(RetentionPolicy.SOURCE)
public @interface SuppressWarnings {

    String[] value();
}

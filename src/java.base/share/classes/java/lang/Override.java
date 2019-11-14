package java.lang;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.annotation.*;

@AnnotatedFor({ "nullness" })
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {
}

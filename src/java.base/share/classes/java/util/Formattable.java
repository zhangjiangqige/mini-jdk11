package java.util;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;

@AnnotatedFor({ "lock", "nullness" })
public interface Formattable {

    void formatTo(Formatter formatter, int flags, int width, int precision);
}

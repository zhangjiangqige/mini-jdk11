package java.lang.reflect;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public interface ParameterizedType extends Type {

    @NonNull
    Type @NonNull [] getActualTypeArguments();

    @NonNull
    Type getRawType();

    Type getOwnerType();
}

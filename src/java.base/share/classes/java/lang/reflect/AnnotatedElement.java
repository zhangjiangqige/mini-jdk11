package java.lang.reflect;

import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationFormatError;
import java.lang.annotation.Repeatable;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import sun.reflect.annotation.AnnotationSupport;
import sun.reflect.annotation.AnnotationType;

@AnnotatedFor({ "lock", "nullness" })
public interface AnnotatedElement {

    @Pure
    default boolean isAnnotationPresent(@GuardSatisfied AnnotatedElement this, Class<? extends Annotation> annotationClass);

    @Nullable
    <T extends @Nullable Annotation> T getAnnotation(Class<T> annotationClass);

    Annotation[] getAnnotations();

    default <T extends Annotation> T[] getAnnotationsByType(Class<T> annotationClass);

    default <T extends Annotation> T getDeclaredAnnotation(Class<T> annotationClass);

    default <T extends Annotation> T[] getDeclaredAnnotationsByType(Class<T> annotationClass);

    Annotation[] getDeclaredAnnotations();
}

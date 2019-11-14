package org.checkerframework.checker.lock.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.InheritedAnnotation;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@InheritedAnnotation
@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR })
public @interface LockingFree {
}

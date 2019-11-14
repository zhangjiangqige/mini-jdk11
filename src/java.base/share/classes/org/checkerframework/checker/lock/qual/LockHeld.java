package org.checkerframework.checker.lock.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.InvisibleQualifier;
import org.checkerframework.framework.qual.SubtypeOf;

@SubtypeOf(LockPossiblyHeld.class)
@InvisibleQualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface LockHeld {
}

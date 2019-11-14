package org.checkerframework.framework.qual;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface NoDefaultQualifierForUse {

    Class<? extends Annotation>[] value() default {};
}

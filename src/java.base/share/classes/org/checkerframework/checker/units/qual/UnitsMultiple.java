package org.checkerframework.checker.units.qual;

import java.lang.annotation.Annotation;

public @interface UnitsMultiple {

    Class<? extends Annotation> quantity();

    Prefix prefix() default Prefix.one;
}

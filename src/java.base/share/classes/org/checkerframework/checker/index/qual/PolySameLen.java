package org.checkerframework.checker.index.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.PolymorphicQualifier;

@PolymorphicQualifier(SameLenUnknown.class)
@Target({ ElementType.TYPE_USE, ElementType.TYPE_PARAMETER })
public @interface PolySameLen {
}

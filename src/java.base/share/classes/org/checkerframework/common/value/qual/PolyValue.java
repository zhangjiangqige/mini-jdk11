package org.checkerframework.common.value.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.PolymorphicQualifier;

@PolymorphicQualifier(UnknownVal.class)
@Target({ ElementType.TYPE_USE, ElementType.TYPE_PARAMETER })
public @interface PolyValue {
}

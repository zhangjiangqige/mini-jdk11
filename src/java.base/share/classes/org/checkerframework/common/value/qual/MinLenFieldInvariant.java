package org.checkerframework.common.value.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.FieldInvariant;

@Inherited
@Target(ElementType.TYPE)
public @interface MinLenFieldInvariant {

    int[] minLen();

    String[] field();
}

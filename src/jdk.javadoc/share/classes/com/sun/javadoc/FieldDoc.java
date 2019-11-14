package com.sun.javadoc;

import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock" })
@Deprecated(since = "9", forRemoval = true)
@SuppressWarnings("removal")
public interface FieldDoc extends MemberDoc {

    Type type();

    boolean isTransient();

    boolean isVolatile();

    SerialFieldTag[] serialFieldTags();

    Object constantValue();

    String constantValueExpression();
}

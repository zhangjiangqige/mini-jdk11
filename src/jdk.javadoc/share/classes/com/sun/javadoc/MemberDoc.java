package com.sun.javadoc;

import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock" })
@Deprecated(since = "9", forRemoval = true)
@SuppressWarnings("removal")
public interface MemberDoc extends ProgramElementDoc {

    boolean isSynthetic();
}

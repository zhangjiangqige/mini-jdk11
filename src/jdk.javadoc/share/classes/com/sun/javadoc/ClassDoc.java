package com.sun.javadoc;

import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock" })
@Deprecated()
@SuppressWarnings("removal")
public interface ClassDoc extends ProgramElementDoc, Type {
}

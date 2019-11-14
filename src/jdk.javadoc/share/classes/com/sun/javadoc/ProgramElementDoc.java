package com.sun.javadoc;

import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock" })
@Deprecated(since = "9", forRemoval = true)
@SuppressWarnings("removal")
public interface ProgramElementDoc extends Doc {

    ClassDoc containingClass();

    PackageDoc containingPackage();

    String qualifiedName();

    int modifierSpecifier();

    String modifiers();

    AnnotationDesc[] annotations();

    boolean isPublic();

    boolean isProtected();

    boolean isPrivate();

    boolean isPackagePrivate();

    boolean isStatic();

    boolean isFinal();
}

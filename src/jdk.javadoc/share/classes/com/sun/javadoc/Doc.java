package com.sun.javadoc;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.text.BreakIterator;
import java.util.Locale;

@AnnotatedFor({ "lock" })
@Deprecated(since = "9", forRemoval = true)
@SuppressWarnings("removal")
public interface Doc extends Comparable<Object> {

    String commentText();

    Tag[] tags();

    Tag[] tags(String tagname);

    SeeTag[] seeTags();

    Tag[] inlineTags();

    Tag[] firstSentenceTags();

    String getRawCommentText();

    void setRawCommentText(String rawDocumentation);

    String name();

    int compareTo(Object obj);

    boolean isField();

    boolean isEnumConstant();

    boolean isConstructor();

    boolean isMethod();

    boolean isAnnotationTypeElement();

    boolean isInterface();

    boolean isException();

    boolean isError();

    boolean isEnum();

    boolean isAnnotationType();

    boolean isOrdinaryClass();

    boolean isClass();

    boolean isIncluded();

    SourcePosition position();
}

package java.lang;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning", "nullness" })
class AssertionStatusDirectives {

    String[] classes;

    boolean[] classEnabled;

    String[] packages;

    boolean[] packageEnabled;

    boolean deflt;
}

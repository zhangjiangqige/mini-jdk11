package java.text;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class EntryPair {

    public String entryName;

    public int value;

    public boolean fwd;

    public EntryPair(String name, int value) {
    }

    public EntryPair(String name, int value, boolean fwd) {
    }
}

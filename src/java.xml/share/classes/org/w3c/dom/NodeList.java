package org.w3c.dom;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor("nullness")
public interface NodeList {

    @Pure
    @Nullable
    public Node item(int index);

    @Pure
    public int getLength();
}

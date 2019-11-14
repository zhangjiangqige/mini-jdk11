package org.w3c.dom;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor("nullness")
public interface Text extends CharacterData {

    public Text splitText(int offset) throws DOMException;

    @Pure
    public boolean isElementContentWhitespace();

    @SideEffectFree
    public String getWholeText();

    @Nullable
    public Text replaceWholeText(String content) throws DOMException;
}

package org.w3c.dom;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public interface Attr extends Node {

    @Pure
    public String getName();

    @Pure
    public boolean getSpecified();

    @Pure
    public String getValue();

    public void setValue(String value) throws DOMException;

    @Pure
    @Nullable
    public Element getOwnerElement();

    @Pure
    public TypeInfo getSchemaTypeInfo();

    @Pure
    public boolean isId();
}

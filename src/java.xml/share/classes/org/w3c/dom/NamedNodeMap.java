package org.w3c.dom;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor("nullness")
public interface NamedNodeMap {

    @Pure
    @Nullable
    public Node getNamedItem(String name);

    @Nullable
    public Node setNamedItem(Node arg) throws DOMException;

    public Node removeNamedItem(String name) throws DOMException;

    @Pure
    @Nullable
    public Node item(int index);

    @Pure
    public int getLength();

    @Pure
    @Nullable
    public Node getNamedItemNS(@Nullable String namespaceURI, String localName) throws DOMException;

    @Nullable
    public Node setNamedItemNS(Node arg) throws DOMException;

    public Node removeNamedItemNS(@Nullable String namespaceURI, String localName) throws DOMException;
}

package org.w3c.dom;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public interface Element extends Node {

    @Pure
    public String getTagName();

    @Pure
    public String getAttribute(String name);

    public void setAttribute(String name, String value) throws DOMException;

    public void removeAttribute(String name) throws DOMException;

    @Pure
    @Nullable
    public Attr getAttributeNode(String name);

    @Nullable
    public Attr setAttributeNode(Attr newAttr) throws DOMException;

    public Attr removeAttributeNode(Attr oldAttr) throws DOMException;

    @Pure
    public NodeList getElementsByTagName(String name);

    @Pure
    public String getAttributeNS(@Nullable String namespaceURI, String localName) throws DOMException;

    public void setAttributeNS(@Nullable String namespaceURI, String qualifiedName, String value) throws DOMException;

    public void removeAttributeNS(@Nullable String namespaceURI, String localName) throws DOMException;

    @Pure
    @Nullable
    public Attr getAttributeNodeNS(@Nullable String namespaceURI, String localName) throws DOMException;

    @Nullable
    public Attr setAttributeNodeNS(Attr newAttr) throws DOMException;

    @Pure
    public NodeList getElementsByTagNameNS(@Nullable String namespaceURI, String localName) throws DOMException;

    @Pure
    public boolean hasAttribute(String name);

    @Pure
    public boolean hasAttributeNS(@Nullable String namespaceURI, String localName) throws DOMException;

    @Pure
    public TypeInfo getSchemaTypeInfo();

    public void setIdAttribute(String name, boolean isId) throws DOMException;

    public void setIdAttributeNS(@Nullable String namespaceURI, String localName, boolean isId) throws DOMException;

    public void setIdAttributeNode(Attr idAttr, boolean isId) throws DOMException;
}

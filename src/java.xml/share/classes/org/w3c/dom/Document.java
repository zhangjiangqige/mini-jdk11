package org.w3c.dom;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor("nullness")
public interface Document extends Node {

    @Pure
    @Nullable
    public DocumentType getDoctype();

    @Pure
    public DOMImplementation getImplementation();

    @Pure
    public Element getDocumentElement();

    public Element createElement(String tagName) throws DOMException;

    public DocumentFragment createDocumentFragment();

    public Text createTextNode(String data);

    public Comment createComment(String data);

    public CDATASection createCDATASection(String data) throws DOMException;

    public ProcessingInstruction createProcessingInstruction(String target, String data) throws DOMException;

    public Attr createAttribute(String name) throws DOMException;

    public EntityReference createEntityReference(String name) throws DOMException;

    @Pure
    public NodeList getElementsByTagName(String tagname);

    public Node importNode(Node importedNode, boolean deep) throws DOMException;

    public Element createElementNS(@Nullable String namespaceURI, String qualifiedName) throws DOMException;

    public Attr createAttributeNS(@Nullable String namespaceURI, String qualifiedName) throws DOMException;

    @Pure
    public NodeList getElementsByTagNameNS(@Nullable String namespaceURI, String localName);

    @Pure
    @Nullable
    public Element getElementById(String elementId);

    @Pure
    @Nullable
    public String getInputEncoding();

    @Pure
    @Nullable
    public String getXmlEncoding();

    @Pure
    public boolean getXmlStandalone();

    public void setXmlStandalone(boolean xmlStandalone) throws DOMException;

    @Pure
    @Nullable
    public String getXmlVersion();

    public void setXmlVersion(String xmlVersion) throws DOMException;

    @Pure
    public boolean getStrictErrorChecking();

    public void setStrictErrorChecking(boolean strictErrorChecking);

    @Pure
    @Nullable
    public String getDocumentURI();

    public void setDocumentURI(@Nullable String documentURI);

    @Nullable
    public Node adoptNode(@Nullable Node source) throws DOMException;

    @Pure
    public DOMConfiguration getDomConfig();

    public void normalizeDocument();

    public Node renameNode(Node n, @Nullable String namespaceURI, String qualifiedName) throws DOMException;
}

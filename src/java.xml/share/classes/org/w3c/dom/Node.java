package org.w3c.dom;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor("nullness")
public interface Node {

    public static final short ELEMENT_NODE;

    public static final short ATTRIBUTE_NODE;

    public static final short TEXT_NODE;

    public static final short CDATA_SECTION_NODE;

    public static final short ENTITY_REFERENCE_NODE;

    public static final short ENTITY_NODE;

    public static final short PROCESSING_INSTRUCTION_NODE;

    public static final short COMMENT_NODE;

    public static final short DOCUMENT_NODE;

    public static final short DOCUMENT_TYPE_NODE;

    public static final short DOCUMENT_FRAGMENT_NODE;

    public static final short NOTATION_NODE;

    @Pure
    public String getNodeName();

    @Pure
    @Nullable
    public String getNodeValue() throws DOMException;

    public void setNodeValue(String nodeValue) throws DOMException;

    @Pure
    public short getNodeType();

    @Pure
    @Nullable
    public Node getParentNode();

    @Pure
    public NodeList getChildNodes();

    @Pure
    @Nullable
    public Node getFirstChild();

    @Pure
    @Nullable
    public Node getLastChild();

    @Pure
    @Nullable
    public Node getPreviousSibling();

    @Pure
    @Nullable
    public Node getNextSibling();

    @Pure
    @Nullable
    public NamedNodeMap getAttributes();

    @Pure
    @Nullable
    public Document getOwnerDocument();

    public Node insertBefore(Node newChild, @Nullable Node refChild) throws DOMException;

    public Node replaceChild(Node newChild, Node oldChild) throws DOMException;

    public Node removeChild(Node oldChild) throws DOMException;

    public Node appendChild(Node newChild) throws DOMException;

    @Pure
    public boolean hasChildNodes();

    public Node cloneNode(boolean deep);

    public void normalize();

    @Pure
    public boolean isSupported(String feature, @Nullable String version);

    @Pure
    @Nullable
    public String getNamespaceURI();

    @Pure
    @Nullable
    public String getPrefix();

    public void setPrefix(@Nullable String prefix) throws DOMException;

    @Pure
    @Nullable
    public String getLocalName();

    @Pure
    public boolean hasAttributes();

    @Pure
    @Nullable
    public String getBaseURI();

    public static final short DOCUMENT_POSITION_DISCONNECTED;

    public static final short DOCUMENT_POSITION_PRECEDING;

    public static final short DOCUMENT_POSITION_FOLLOWING;

    public static final short DOCUMENT_POSITION_CONTAINS;

    public static final short DOCUMENT_POSITION_CONTAINED_BY;

    public static final short DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC;

    @Pure
    public short compareDocumentPosition(Node other) throws DOMException;

    @Pure
    @Nullable
    public String getTextContent() throws DOMException;

    public void setTextContent(String textContent) throws DOMException;

    @Pure
    public boolean isSameNode(Node other);

    @Pure
    @Nullable
    public String lookupPrefix(@Nullable String namespaceURI);

    @Pure
    public boolean isDefaultNamespace(@Nullable String namespaceURI);

    @Pure
    @Nullable
    public String lookupNamespaceURI(@Nullable String prefix);

    @Pure
    public boolean isEqualNode(Node arg);

    @Pure
    @Nullable
    public Object getFeature(String feature, @Nullable String version);

    @Nullable
    public Object setUserData(String key, @Nullable Object data, @Nullable UserDataHandler handler);

    @Pure
    @Nullable
    public Object getUserData(String key);
}

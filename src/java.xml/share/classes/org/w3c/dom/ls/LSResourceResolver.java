package org.w3c.dom.ls;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public interface LSResourceResolver {

    @Pure
    @Nullable
    public LSInput resolveResource(String type, @Nullable String namespaceURI, @Nullable String publicId, @Nullable String systemId, @Nullable String baseURI);
}

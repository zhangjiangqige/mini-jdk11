package org.xml.sax;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;

@AnnotatedFor("nullness")
public interface EntityResolver {

    @Pure
    @Nullable
    public abstract InputSource resolveEntity(@Nullable String publicId, String systemId) throws SAXException, IOException;
}

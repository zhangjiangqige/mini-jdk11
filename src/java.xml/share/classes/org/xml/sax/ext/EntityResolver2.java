package org.xml.sax.ext;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;

@AnnotatedFor("nullness")
public interface EntityResolver2 extends EntityResolver {

    @Pure
    @Nullable
    public InputSource getExternalSubset(String name, @Nullable String baseURI) throws SAXException, IOException;

    @Pure
    @Nullable
    public InputSource resolveEntity(String name, @Nullable String publicId, @Nullable String baseURI, String systemId) throws SAXException, IOException;
}

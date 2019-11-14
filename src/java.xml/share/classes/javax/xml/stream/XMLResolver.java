package javax.xml.stream;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor("nullness")
public interface XMLResolver {

    @Pure
    @Nullable
    public Object resolveEntity(@Nullable String publicID, String systemID, @Nullable String baseURI, @Nullable String namespace) throws XMLStreamException;
}

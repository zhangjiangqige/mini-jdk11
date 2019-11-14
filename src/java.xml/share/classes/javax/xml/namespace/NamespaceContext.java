package javax.xml.namespace;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Iterator;

@AnnotatedFor("nullness")
public interface NamespaceContext {

    @Pure
    String getNamespaceURI(String prefix);

    @Nullable
    @Pure
    String getPrefix(String namespaceURI);

    @Pure
    Iterator<String> getPrefixes(String namespaceURI);
}

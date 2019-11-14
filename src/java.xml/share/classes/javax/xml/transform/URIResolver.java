package javax.xml.transform;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor("nullness")
public interface URIResolver {

    @Pure
    @Nullable
    public Source resolve(String href, @Nullable String base) throws TransformerException;
}

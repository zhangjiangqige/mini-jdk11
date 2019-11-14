package javax.xml.xpath;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.xml.namespace.QName;

@AnnotatedFor("nullness")
public interface XPathVariableResolver {

    @Pure
    @Nullable
    public Object resolveVariable(QName variableName);
}

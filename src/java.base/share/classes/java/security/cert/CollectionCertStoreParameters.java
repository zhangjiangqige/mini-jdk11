package java.security.cert;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class CollectionCertStoreParameters implements CertStoreParameters {

    public CollectionCertStoreParameters(Collection<?> collection) {
    }

    public CollectionCertStoreParameters() {
    }

    public Collection<?> getCollection();

    public Object clone();

    public String toString();
}

package java.security.cert;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class CollectionCertStoreParameters implements CertStoreParameters {

    private Collection<?> coll;

    public CollectionCertStoreParameters(Collection<?> collection) {
        if (collection == null)
            throw new NullPointerException();
        coll = collection;
    }

    public CollectionCertStoreParameters() {
        coll = Collections.EMPTY_SET;
    }

    public Collection<?> getCollection();

    public Object clone();

    public String toString();
}

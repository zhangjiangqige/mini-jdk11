package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class URIParameter implements Policy.Parameters, javax.security.auth.login.Configuration.Parameters {

    public URIParameter(java.net.URI uri) {
    }

    public java.net.URI getURI();
}

package java.nio.file.attribute;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class UserPrincipalLookupService {

    protected UserPrincipalLookupService() {
    }

    public abstract UserPrincipal lookupPrincipalByName(String name) throws IOException;

    public abstract GroupPrincipal lookupPrincipalByGroupName(String group) throws IOException;
}

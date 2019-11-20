package java.net;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class PasswordAuthentication {

    public PasswordAuthentication(String userName, char[] password) {
    }

    public String getUserName();

    public char[] getPassword();
}

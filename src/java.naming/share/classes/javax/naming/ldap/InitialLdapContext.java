package javax.naming.ldap;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.naming.*;
import javax.naming.directory.*;
import java.util.Hashtable;

@AnnotatedFor("nullness")
public class InitialLdapContext extends InitialDirContext implements LdapContext {

    public InitialLdapContext() throws NamingException {
    }

    @SuppressWarnings("unchecked")
    public InitialLdapContext(@Nullable Hashtable<?, ?> environment, Control @Nullable [] connCtls) throws NamingException {
    }

    @Nullable
    public ExtendedResponse extendedOperation(ExtendedRequest request) throws NamingException;

    public LdapContext newInstance(Control @Nullable [] reqCtls) throws NamingException;

    public void reconnect(Control @Nullable [] connCtls) throws NamingException;

    public Control @Nullable [] getConnectControls() throws NamingException;

    public void setRequestControls(Control @Nullable [] requestControls) throws NamingException;

    public Control @Nullable [] getRequestControls() throws NamingException;

    public Control @Nullable [] getResponseControls() throws NamingException;
}

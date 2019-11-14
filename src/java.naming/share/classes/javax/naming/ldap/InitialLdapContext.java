package javax.naming.ldap;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import javax.naming.*;
import javax.naming.directory.*;
import java.util.Hashtable;

@AnnotatedFor("nullness")
public class InitialLdapContext extends InitialDirContext implements LdapContext {

    private static final String BIND_CONTROLS_PROPERTY = "java.naming.ldap.control.connect";

    public InitialLdapContext() throws NamingException {
        super(null);
    }

    @SuppressWarnings("unchecked")
    public InitialLdapContext(@Nullable Hashtable<?, ?> environment, Control @Nullable [] connCtls) throws NamingException {
        super(true);
        Hashtable<Object, Object> env = (environment == null) ? new Hashtable<>(11) : (Hashtable<Object, Object>) environment.clone();
        if (connCtls != null) {
            Control[] copy = new Control[connCtls.length];
            System.arraycopy(connCtls, 0, copy, 0, connCtls.length);
            env.put(BIND_CONTROLS_PROPERTY, copy);
        }
        env.put("java.naming.ldap.version", "3");
        init(env);
    }

    private LdapContext getDefaultLdapInitCtx() throws NamingException;

    @Nullable
    public ExtendedResponse extendedOperation(ExtendedRequest request) throws NamingException;

    public LdapContext newInstance(Control @Nullable [] reqCtls) throws NamingException;

    public void reconnect(Control @Nullable [] connCtls) throws NamingException;

    public Control @Nullable [] getConnectControls() throws NamingException;

    public void setRequestControls(Control @Nullable [] requestControls) throws NamingException;

    public Control @Nullable [] getRequestControls() throws NamingException;

    public Control @Nullable [] getResponseControls() throws NamingException;
}

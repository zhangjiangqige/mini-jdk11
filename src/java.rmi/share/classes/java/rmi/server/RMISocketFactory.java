package java.rmi.server;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.net.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class RMISocketFactory implements RMIClientSocketFactory, RMIServerSocketFactory {

    public RMISocketFactory() {
    }

    public abstract Socket createSocket(String host, int port) throws IOException;

    public abstract ServerSocket createServerSocket(int port) throws IOException;

    public synchronized static void setSocketFactory(RMISocketFactory fac) throws IOException;

    public synchronized static RMISocketFactory getSocketFactory();

    public synchronized static RMISocketFactory getDefaultSocketFactory();

    public synchronized static void setFailureHandler(RMIFailureHandler fh);

    public synchronized static RMIFailureHandler getFailureHandler();
}

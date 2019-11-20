package java.rmi.registry;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.rmi.RemoteException;
import java.rmi.server.ObjID;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;
import sun.rmi.registry.RegistryImpl;
import sun.rmi.server.UnicastRef2;
import sun.rmi.server.UnicastRef;
import sun.rmi.server.Util;
import sun.rmi.transport.LiveRef;
import sun.rmi.transport.tcp.TCPEndpoint;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class LocateRegistry {

    public static Registry getRegistry() throws RemoteException;

    public static Registry getRegistry(int port) throws RemoteException;

    public static Registry getRegistry(String host) throws RemoteException;

    public static Registry getRegistry(String host, int port) throws RemoteException;

    public static Registry getRegistry(String host, int port, RMIClientSocketFactory csf) throws RemoteException;

    public static Registry createRegistry(int port) throws RemoteException;

    public static Registry createRegistry(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException;
}

package java.rmi;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.rmi.registry.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@AnnotatedFor({ "interning" })
public final class Naming {

    public static Remote lookup(String name) throws NotBoundException, java.net.MalformedURLException, RemoteException;

    public static void bind(String name, Remote obj) throws AlreadyBoundException, java.net.MalformedURLException, RemoteException;

    public static void unbind(String name) throws RemoteException, NotBoundException, java.net.MalformedURLException;

    public static void rebind(String name, Remote obj) throws RemoteException, java.net.MalformedURLException;

    public static String[] list(String name) throws RemoteException, java.net.MalformedURLException;
}

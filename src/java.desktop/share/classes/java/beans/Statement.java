package java.beans;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import com.sun.beans.finder.ClassFinder;
import com.sun.beans.finder.ConstructorFinder;
import com.sun.beans.finder.MethodFinder;
import sun.reflect.misc.MethodUtil;
import static sun.reflect.misc.ReflectUtil.checkPackageAccess;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Statement {

    private static Object[] emptyArray = new Object[] {};

    static ExceptionListener defaultExceptionListener = new ExceptionListener() {

        public void exceptionThrown(Exception e) {
            System.err.println(e);
            System.err.println("Continuing ...");
        }
    };

    private final AccessControlContext acc = AccessController.getContext();

    private final Object target;

    private final String methodName;

    private final Object[] arguments;

    ClassLoader loader;

    @ConstructorProperties({ "target", "methodName", "arguments" })
    public Statement(Object target, String methodName, Object[] arguments) {
        this.target = target;
        this.methodName = methodName;
        this.arguments = (arguments == null) ? emptyArray : arguments.clone();
    }

    public Object getTarget();

    public String getMethodName();

    public Object[] getArguments();

    public void execute() throws Exception;

    Object invoke() throws Exception;

    private Object invokeInternal() throws Exception;

    String instanceName(Object instance);

    public String toString();

    static Method getMethod(Class<?> type, String name, Class<?>... args);
}

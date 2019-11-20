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

    @ConstructorProperties({ "target", "methodName", "arguments" })
    public Statement(Object target, String methodName, Object[] arguments) {
    }

    public Object getTarget();

    public String getMethodName();

    public Object[] getArguments();

    public void execute() throws Exception;

    public String toString();
}

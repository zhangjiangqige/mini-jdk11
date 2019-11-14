package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.*;
import java.lang.module.ModuleFinder;

@AnnotatedFor({ "nullnes" })
public final class RuntimePermission extends BasicPermission {

    private static final long serialVersionUID = 7399184964622342223L;

    public RuntimePermission(String name) {
        super(name);
    }

    public RuntimePermission(String name, @Nullable String actions) {
        super(name, actions);
    }
}

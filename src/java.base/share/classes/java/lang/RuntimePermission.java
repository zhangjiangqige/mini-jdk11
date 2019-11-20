package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.*;
import java.lang.module.ModuleFinder;

@AnnotatedFor({ "nullnes" })
public final class RuntimePermission extends BasicPermission {

    public RuntimePermission(String name) {
    }

    public RuntimePermission(String name, @Nullable String actions) {
    }
}

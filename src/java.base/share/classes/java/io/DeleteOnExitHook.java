package java.io;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.*;
import java.io.File;
import jdk.internal.misc.SharedSecrets;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
class DeleteOnExitHook {

    private static LinkedHashSet<String> files = new LinkedHashSet<>();

    static {
        SharedSecrets.getJavaLangAccess().registerShutdownHook(2, true, new Runnable() {

            public void run() {
                runHooks();
            }
        });
    }

    private DeleteOnExitHook() {
    }

    static synchronized void add(String file);

    static void runHooks();
}

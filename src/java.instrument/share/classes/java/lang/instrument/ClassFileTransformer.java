package java.lang.instrument;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signature.qual.InternalForm;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

@AnnotatedFor({ "nullness", "signature" })
public interface ClassFileTransformer {

    default byte @Nullable [] transform(@Nullable ClassLoader loader, @InternalForm String className, @Nullable Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException;

    default byte[] transform(Module module, ClassLoader loader, @InternalForm String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException;
}

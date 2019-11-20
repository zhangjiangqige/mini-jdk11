package java.lang.instrument;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signature.qual.InternalForm;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

@AnnotatedFor({ "nullness", "signature" })
public interface ClassFileTransformer {
}

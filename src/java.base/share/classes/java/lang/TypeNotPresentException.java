package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signature.qual.FullyQualifiedName;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness", "signature" })
public class TypeNotPresentException extends RuntimeException {

    @SideEffectFree
    public TypeNotPresentException(@FullyQualifiedName String typeName, @Nullable Throwable cause) {
    }

    @FullyQualifiedName
    public String typeName();
}

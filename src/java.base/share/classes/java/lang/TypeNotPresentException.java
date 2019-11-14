package java.lang;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signature.qual.FullyQualifiedName;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness", "signature" })
public class TypeNotPresentException extends RuntimeException {

    private static final long serialVersionUID = -5101214195716534496L;

    private String typeName;

    @SideEffectFree
    public TypeNotPresentException(@FullyQualifiedName String typeName, @Nullable Throwable cause) {
        super("Type " + typeName + " not present", cause);
        this.typeName = typeName;
    }

    @FullyQualifiedName
    public String typeName();
}

package java.lang.instrument;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class ClassDefinition {

    public ClassDefinition(Class<?> theClass, byte[] theClassFile) {
    }

    public Class<?> getDefinitionClass();

    public byte[] getDefinitionClassFile();
}

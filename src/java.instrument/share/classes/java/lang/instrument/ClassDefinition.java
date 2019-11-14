package java.lang.instrument;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class ClassDefinition {

    private final Class<?> mClass;

    private final byte[] mClassFile;

    public ClassDefinition(Class<?> theClass, byte[] theClassFile) {
        if (theClass == null || theClassFile == null) {
            throw new NullPointerException();
        }
        mClass = theClass;
        mClassFile = theClassFile;
    }

    public Class<?> getDefinitionClass();

    public byte[] getDefinitionClassFile();
}

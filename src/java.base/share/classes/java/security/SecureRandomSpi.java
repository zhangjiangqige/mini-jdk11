package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class SecureRandomSpi implements java.io.Serializable {

    private static final long serialVersionUID = -2991854161009191830L;

    public SecureRandomSpi() {
    }

    protected SecureRandomSpi(SecureRandomParameters params) {
    }

    protected abstract void engineSetSeed(byte[] seed);

    protected abstract void engineNextBytes(byte[] bytes);

    protected void engineNextBytes(byte[] bytes, SecureRandomParameters params);

    protected abstract byte[] engineGenerateSeed(int numBytes);

    protected void engineReseed(SecureRandomParameters params);

    protected SecureRandomParameters engineGetParameters();

    @Override
    public String toString();
}

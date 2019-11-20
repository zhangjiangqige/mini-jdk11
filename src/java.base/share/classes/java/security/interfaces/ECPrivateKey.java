package java.security.interfaces;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.math.BigInteger;
import java.security.PrivateKey;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public interface ECPrivateKey extends PrivateKey, ECKey {
}

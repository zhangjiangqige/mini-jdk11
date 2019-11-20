package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class SignedObject implements Serializable {

    public SignedObject(Serializable object, PrivateKey signingKey, Signature signingEngine) throws IOException, InvalidKeyException, SignatureException {
    }

    public Object getObject() throws IOException, ClassNotFoundException;

    public byte[] getSignature();

    public String getAlgorithm();

    public boolean verify(PublicKey verificationKey, Signature verificationEngine) throws InvalidKeyException, SignatureException;
}

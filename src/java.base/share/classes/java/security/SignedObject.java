package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class SignedObject implements Serializable {

    private static final long serialVersionUID = 720502720485447167L;

    private byte[] content;

    private byte[] signature;

    private String thealgorithm;

    public SignedObject(Serializable object, PrivateKey signingKey, Signature signingEngine) throws IOException, InvalidKeyException, SignatureException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutput a = new ObjectOutputStream(b);
        a.writeObject(object);
        a.flush();
        a.close();
        this.content = b.toByteArray();
        b.close();
        this.sign(signingKey, signingEngine);
    }

    public Object getObject() throws IOException, ClassNotFoundException;

    public byte[] getSignature();

    public String getAlgorithm();

    public boolean verify(PublicKey verificationKey, Signature verificationEngine) throws InvalidKeyException, SignatureException;

    private void sign(PrivateKey signingKey, Signature signingEngine) throws InvalidKeyException, SignatureException;

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException;
}

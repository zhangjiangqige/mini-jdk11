package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.spec.AlgorithmParameterSpec;
import java.util.*;
import java.io.*;
import java.nio.ByteBuffer;
import sun.security.jca.JCAUtil;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class SignatureSpi {

    protected SecureRandom appRandom = null;

    protected abstract void engineInitVerify(PublicKey publicKey) throws InvalidKeyException;

    protected abstract void engineInitSign(PrivateKey privateKey) throws InvalidKeyException;

    protected void engineInitSign(PrivateKey privateKey, SecureRandom random) throws InvalidKeyException;

    protected abstract void engineUpdate(byte b) throws SignatureException;

    protected abstract void engineUpdate(byte[] b, int off, int len) throws SignatureException;

    protected void engineUpdate(ByteBuffer input);

    protected abstract byte[] engineSign() throws SignatureException;

    protected int engineSign(byte[] outbuf, int offset, int len) throws SignatureException;

    protected abstract boolean engineVerify(byte[] sigBytes) throws SignatureException;

    protected boolean engineVerify(byte[] sigBytes, int offset, int length) throws SignatureException;

    @Deprecated
    protected abstract void engineSetParameter(String param, Object value) throws InvalidParameterException;

    protected void engineSetParameter(AlgorithmParameterSpec params) throws InvalidAlgorithmParameterException;

    protected AlgorithmParameters engineGetParameters();

    @Deprecated
    protected abstract Object engineGetParameter(String param) throws InvalidParameterException;

    public Object clone() throws CloneNotSupportedException;
}

package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.util.Objects;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class AlgorithmParameters {

    private Provider provider;

    private AlgorithmParametersSpi paramSpi;

    private String algorithm;

    private boolean initialized = false;

    protected AlgorithmParameters(AlgorithmParametersSpi paramSpi, Provider provider, String algorithm) {
        this.paramSpi = paramSpi;
        this.provider = provider;
        this.algorithm = algorithm;
    }

    public final String getAlgorithm();

    public static AlgorithmParameters getInstance(String algorithm) throws NoSuchAlgorithmException;

    public static AlgorithmParameters getInstance(String algorithm, String provider) throws NoSuchAlgorithmException, NoSuchProviderException;

    public static AlgorithmParameters getInstance(String algorithm, Provider provider) throws NoSuchAlgorithmException;

    public final Provider getProvider();

    public final void init(AlgorithmParameterSpec paramSpec) throws InvalidParameterSpecException;

    public final void init(byte[] params) throws IOException;

    public final void init(byte[] params, String format) throws IOException;

    public final <T extends AlgorithmParameterSpec> T getParameterSpec(Class<T> paramSpec) throws InvalidParameterSpecException;

    public final byte[] getEncoded() throws IOException;

    public final byte[] getEncoded(String format) throws IOException;

    public final String toString();
}

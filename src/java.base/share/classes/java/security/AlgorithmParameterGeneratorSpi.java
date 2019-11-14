package java.security;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.security.spec.AlgorithmParameterSpec;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class AlgorithmParameterGeneratorSpi {

    protected abstract void engineInit(int size, SecureRandom random);

    protected abstract void engineInit(AlgorithmParameterSpec genParamSpec, SecureRandom random) throws InvalidAlgorithmParameterException;

    protected abstract AlgorithmParameters engineGenerateParameters();
}

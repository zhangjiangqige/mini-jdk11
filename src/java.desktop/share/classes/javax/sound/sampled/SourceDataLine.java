package javax.sound.sampled;

import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "signedness" })
public interface SourceDataLine extends DataLine {

    void open(AudioFormat format, int bufferSize) throws LineUnavailableException;

    void open(AudioFormat format) throws LineUnavailableException;

    int write(@PolySigned byte[] b, int off, int len);
}

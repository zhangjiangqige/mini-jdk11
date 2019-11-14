package org.xml.sax;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStream;

@AnnotatedFor("nullness")
public class InputSource {

    @SideEffectFree
    public InputSource() {
    }

    @SideEffectFree
    public InputSource(String systemId) {
        setSystemId(systemId);
    }

    @SideEffectFree
    public InputSource(InputStream byteStream) {
        setByteStream(byteStream);
    }

    @SideEffectFree
    public InputSource(Reader characterStream) {
        setCharacterStream(characterStream);
    }

    public void setPublicId(@Nullable String publicId);

    @Pure
    @Nullable
    public String getPublicId();

    public void setSystemId(@Nullable String systemId);

    @Pure
    @Nullable
    public String getSystemId();

    public void setByteStream(@Nullable InputStream byteStream);

    @Pure
    @Nullable
    public InputStream getByteStream();

    public void setEncoding(@Nullable String encoding);

    @Pure
    @Nullable
    public String getEncoding();

    public void setCharacterStream(@Nullable Reader characterStream);

    @Pure
    @Nullable
    public Reader getCharacterStream();

    public boolean isEmpty();

    private boolean isStreamEmpty();

    @Nullable
    private String publicId;

    @Nullable
    private String systemId;

    @Nullable
    private InputStream byteStream;

    @Nullable
    private String encoding;

    @Nullable
    private Reader characterStream;
}

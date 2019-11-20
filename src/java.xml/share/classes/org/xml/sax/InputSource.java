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
    }

    @SideEffectFree
    public InputSource(InputStream byteStream) {
    }

    @SideEffectFree
    public InputSource(Reader characterStream) {
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
}

package org.w3c.dom.ls;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
public interface LSInput {

    @Pure
    public java.io.@Nullable Reader getCharacterStream();

    public void setCharacterStream(java.io.@Nullable Reader characterStream);

    @Pure
    public java.io.@Nullable InputStream getByteStream();

    public void setByteStream(java.io.@Nullable InputStream byteStream);

    @Pure
    @Nullable
    public String getStringData();

    public void setStringData(@Nullable String stringData);

    @Pure
    @Nullable
    public String getSystemId();

    public void setSystemId(@Nullable String systemId);

    @Pure
    @Nullable
    public String getPublicId();

    public void setPublicId(@Nullable String publicId);

    @Pure
    @Nullable
    public String getBaseURI();

    public void setBaseURI(@Nullable String baseURI);

    @Pure
    @Nullable
    public String getEncoding();

    public void setEncoding(@Nullable String encoding);

    @Pure
    public boolean getCertifiedText();

    public void setCertifiedText(boolean certifiedText);
}

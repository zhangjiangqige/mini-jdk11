package java.util.jar;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.security.CodeSigner;
import java.security.cert.Certificate;

@AnnotatedFor({ "nullness" })
public class JarEntry extends ZipEntry {

    public JarEntry(String name) {
    }

    public JarEntry(ZipEntry ze) {
    }

    public JarEntry(JarEntry je) {
    }

    @Nullable
    public Attributes getAttributes() throws IOException;

    public Certificate @Nullable [] getCertificates();

    public CodeSigner @Nullable [] getCodeSigners();

    public String getRealName();
}

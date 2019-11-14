package java.util.jar;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.security.CodeSigner;
import java.security.cert.Certificate;

@AnnotatedFor({ "nullness" })
public class JarEntry extends ZipEntry {

    Attributes attr;

    Certificate[] certs;

    CodeSigner[] signers;

    public JarEntry(String name) {
        super(name);
    }

    public JarEntry(ZipEntry ze) {
        super(ze);
    }

    public JarEntry(JarEntry je) {
        this((ZipEntry) je);
        this.attr = je.attr;
        this.certs = je.certs;
        this.signers = je.signers;
    }

    @Nullable
    public Attributes getAttributes() throws IOException;

    public Certificate @Nullable [] getCertificates();

    public CodeSigner @Nullable [] getCodeSigners();

    public String getRealName();
}

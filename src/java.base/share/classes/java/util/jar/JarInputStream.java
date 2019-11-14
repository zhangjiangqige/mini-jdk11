package java.util.jar;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.zip.*;
import java.io.*;
import sun.security.util.ManifestEntryVerifier;
import jdk.internal.util.jar.JarIndex;

@AnnotatedFor({ "nullness" })
public class JarInputStream extends ZipInputStream {

    @Nullable
    private Manifest man;

    @Nullable
    private JarEntry first;

    @Nullable
    private JarVerifier jv;

    @Nullable
    private ManifestEntryVerifier mev;

    private final boolean doVerify;

    private boolean tryManifest;

    public JarInputStream(InputStream in) throws IOException {
        this(in, true);
    }

    public JarInputStream(InputStream in, boolean verify) throws IOException {
        super(in);
        this.doVerify = verify;
        JarEntry e = (JarEntry) super.getNextEntry();
        if (e != null && e.getName().equalsIgnoreCase("META-INF/"))
            e = (JarEntry) super.getNextEntry();
        first = checkManifest(e);
    }

    private JarEntry checkManifest(JarEntry e) throws IOException;

    private byte[] getBytes(InputStream is) throws IOException;

    @Nullable
    public Manifest getManifest();

    public ZipEntry getNextEntry() throws IOException;

    @Nullable
    public JarEntry getNextJarEntry() throws IOException;

    public int read(byte[] b, int off, int len) throws IOException;

    protected ZipEntry createZipEntry(String name);
}

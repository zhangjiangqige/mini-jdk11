package java.util.jar;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.zip.*;
import java.io.*;
import sun.security.util.ManifestEntryVerifier;
import jdk.internal.util.jar.JarIndex;

@AnnotatedFor({ "nullness" })
public class JarInputStream extends ZipInputStream {

    public JarInputStream(InputStream in) throws IOException {
    }

    public JarInputStream(InputStream in, boolean verify) throws IOException {
    }

    @Nullable
    public Manifest getManifest();

    public ZipEntry getNextEntry() throws IOException;

    @Nullable
    public JarEntry getNextJarEntry() throws IOException;

    public int read(byte[] b, int off, int len) throws IOException;

    protected ZipEntry createZipEntry(String name);
}

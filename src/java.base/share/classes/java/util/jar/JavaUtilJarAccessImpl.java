package java.util.jar;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.Enumeration;
import java.util.List;
import jdk.internal.misc.JavaUtilJarAccess;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class JavaUtilJarAccessImpl implements JavaUtilJarAccess {

    public boolean jarFileHasClassPathAttribute(JarFile jar) throws IOException;

    public CodeSource[] getCodeSources(JarFile jar, URL url);

    public CodeSource getCodeSource(JarFile jar, URL url, String name);

    public Enumeration<String> entryNames(JarFile jar, CodeSource[] cs);

    public Enumeration<JarEntry> entries2(JarFile jar);

    public void setEagerValidation(JarFile jar, boolean eager);

    public List<Object> getManifestDigests(JarFile jar);
}

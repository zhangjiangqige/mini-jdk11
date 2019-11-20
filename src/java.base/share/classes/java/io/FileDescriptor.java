package java.io;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jdk.internal.misc.JavaIOFileDescriptorAccess;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.ref.PhantomCleanable;

@AnnotatedFor({ "nullness", "index" })
public final class FileDescriptor {

    public FileDescriptor() {
    }

    public static final FileDescriptor in;

    public static final FileDescriptor out;

    public static final FileDescriptor err;

    public boolean valid();

    public native void sync() throws SyncFailedException;
}

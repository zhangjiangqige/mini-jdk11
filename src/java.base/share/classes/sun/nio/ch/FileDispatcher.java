package sun.nio.ch;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.channels.SelectableChannel;

@AnnotatedFor({ "index" })
abstract class FileDispatcher extends NativeDispatcher {

    public static final int NO_LOCK = -1;

    public static final int LOCKED = 0;

    public static final int RET_EX_LOCK = 1;

    public static final int INTERRUPTED = 2;

    abstract long seek(FileDescriptor fd, long offset) throws IOException;

    abstract int force(FileDescriptor fd, boolean metaData) throws IOException;

    abstract int truncate(FileDescriptor fd, long size) throws IOException;

    abstract long size(FileDescriptor fd) throws IOException;

    abstract int lock(FileDescriptor fd, boolean blocking, long pos, long size, boolean shared) throws IOException;

    abstract void release(FileDescriptor fd, long pos, long size) throws IOException;

    abstract FileDescriptor duplicateForMapping(FileDescriptor fd) throws IOException;

    abstract boolean canTransferToDirectly(SelectableChannel sc);

    abstract boolean transferToDirectlyNeedsPositionLock();

    abstract int setDirectIO(FileDescriptor fd, String path);
}

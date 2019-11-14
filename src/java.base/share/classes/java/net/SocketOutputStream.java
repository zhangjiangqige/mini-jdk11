package java.net;

import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

@AnnotatedFor({ "signedness" })
class SocketOutputStream extends FileOutputStream {

    static {
        init();
    }

    private AbstractPlainSocketImpl impl = null;

    private byte[] temp = new byte[1];

    private Socket socket = null;

    SocketOutputStream(AbstractPlainSocketImpl impl) throws IOException {
        super(impl.getFileDescriptor());
        this.impl = impl;
        socket = impl.getSocket();
    }

    public final FileChannel getChannel();

    private native void socketWrite0(FileDescriptor fd, @PolySigned byte[] b, int off, int len) throws IOException;

    private void socketWrite(@PolySigned byte[] b, int off, int len) throws IOException;

    public void write(int b) throws IOException;

    public void write(@PolySigned byte[] b) throws IOException;

    public void write(@PolySigned byte[] b, int off, int len) throws IOException;

    private boolean closing = false;

    public void close() throws IOException;

    @SuppressWarnings({ "deprecation", "removal" })
    protected void finalize();

    private static native void init();
}

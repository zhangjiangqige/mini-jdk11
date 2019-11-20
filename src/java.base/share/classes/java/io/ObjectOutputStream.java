package java.io;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LTLengthOf;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.signedness.qual.PolySigned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.ObjectStreamClass.WeakClassKey;
import java.lang.ref.ReferenceQueue;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import static java.io.ObjectStreamClass.processQueue;
import sun.reflect.misc.ReflectUtil;

@AnnotatedFor({ "nullness", "index", "signedness" })
public class ObjectOutputStream extends OutputStream implements ObjectOutput, ObjectStreamConstants {

    public ObjectOutputStream(OutputStream out) throws IOException {
    }

    protected ObjectOutputStream() throws IOException, SecurityException {
    }

    public void useProtocolVersion(int version) throws IOException;

    public final void writeObject(@Nullable Object obj) throws IOException;

    protected void writeObjectOverride(Object obj) throws IOException;

    public void writeUnshared(@Nullable Object obj) throws IOException;

    public void defaultWriteObject() throws IOException;

    public ObjectOutputStream.PutField putFields() throws IOException;

    public void writeFields() throws IOException;

    public void reset() throws IOException;

    protected void annotateClass(Class<?> cl) throws IOException;

    protected void annotateProxyClass(Class<?> cl) throws IOException;

    protected Object replaceObject(Object obj) throws IOException;

    protected boolean enableReplaceObject(boolean enable) throws SecurityException;

    protected void writeStreamHeader() throws IOException;

    protected void writeClassDescriptor(ObjectStreamClass desc) throws IOException;

    public void write(@PolySigned int val) throws IOException;

    public void write(@PolySigned byte[] buf) throws IOException;

    public void write(@PolySigned byte[] buf, @IndexOrHigh({ "#1" }) int off, @LTLengthOf(value = { "#1" }, offset = { "#2 - 1" }) @NonNegative int len) throws IOException;

    public void flush() throws IOException;

    protected void drain() throws IOException;

    public void close() throws IOException;

    public void writeBoolean(boolean val) throws IOException;

    public void writeByte(int val) throws IOException;

    public void writeShort(int val) throws IOException;

    public void writeChar(int val) throws IOException;

    public void writeInt(int val) throws IOException;

    public void writeLong(long val) throws IOException;

    public void writeFloat(float val) throws IOException;

    public void writeDouble(double val) throws IOException;

    public void writeBytes(String str) throws IOException;

    public void writeChars(String str) throws IOException;

    public void writeUTF(String str) throws IOException;

    public abstract static class PutField {

        public abstract void put(String name, boolean val);

        public abstract void put(String name, byte val);

        public abstract void put(String name, char val);

        public abstract void put(String name, short val);

        public abstract void put(String name, int val);

        public abstract void put(String name, long val);

        public abstract void put(String name, float val);

        public abstract void put(String name, double val);

        public abstract void put(String name, @Nullable Object val);

        @Deprecated
        public abstract void write(ObjectOutput out) throws IOException;
    }
}

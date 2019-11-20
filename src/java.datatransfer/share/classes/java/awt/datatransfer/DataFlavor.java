package java.awt.datatransfer;

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.OptionalDataException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import sun.datatransfer.DataFlavorUtil;
import sun.reflect.misc.ReflectUtil;

@AnnotatedFor({ "interning" })
public class DataFlavor implements Externalizable, Cloneable {

    protected static final Class<?> tryToLoadClass(String className, ClassLoader fallback) throws ClassNotFoundException;

    public static final DataFlavor stringFlavor;

    public static final DataFlavor imageFlavor;

    @Deprecated
    public static final DataFlavor plainTextFlavor;

    @Interned
    public static final String javaSerializedObjectMimeType;

    public static final DataFlavor javaFileListFlavor;

    @Interned
    public static final String javaJVMLocalObjectMimeType;

    @Interned
    public static final String javaRemoteObjectMimeType;

    public static DataFlavor selectionHtmlFlavor;

    public static DataFlavor fragmentHtmlFlavor;

    public static DataFlavor allHtmlFlavor;

    public DataFlavor() {
    }

    public DataFlavor(Class<?> representationClass, String humanPresentableName) {
    }

    public DataFlavor(String mimeType, String humanPresentableName) {
    }

    public DataFlavor(String mimeType, String humanPresentableName, ClassLoader classLoader) throws ClassNotFoundException {
    }

    public DataFlavor(String mimeType) throws ClassNotFoundException {
    }

    public String toString();

    public static final DataFlavor getTextPlainUnicodeFlavor();

    public static final DataFlavor selectBestTextFlavor(DataFlavor[] availableFlavors);

    public Reader getReaderForText(Transferable transferable) throws UnsupportedFlavorException, IOException;

    public String getMimeType();

    public Class<?> getRepresentationClass();

    public String getHumanPresentableName();

    public String getPrimaryType();

    public String getSubType();

    public String getParameter(String paramName);

    public void setHumanPresentableName(String humanPresentableName);

    public boolean equals(Object o);

    public boolean equals(DataFlavor that);

    @Deprecated
    public boolean equals(String s);

    public int hashCode();

    public boolean match(DataFlavor that);

    public boolean isMimeTypeEqual(String mimeType);

    public final boolean isMimeTypeEqual(DataFlavor dataFlavor);

    public boolean isMimeTypeSerializedObject();

    public final Class<?> getDefaultRepresentationClass();

    public final String getDefaultRepresentationClassAsString();

    public boolean isRepresentationClassInputStream();

    public boolean isRepresentationClassReader();

    public boolean isRepresentationClassCharBuffer();

    public boolean isRepresentationClassByteBuffer();

    public boolean isRepresentationClassSerializable();

    public boolean isRepresentationClassRemote();

    public boolean isFlavorSerializedObjectType();

    public boolean isFlavorRemoteObjectType();

    public boolean isFlavorJavaFileListType();

    public boolean isFlavorTextType();

    public synchronized void writeExternal(ObjectOutput os) throws IOException;

    public synchronized void readExternal(ObjectInput is) throws IOException, ClassNotFoundException;

    public Object clone() throws CloneNotSupportedException;

    @Deprecated
    protected String normalizeMimeTypeParameter(String parameterName, String parameterValue);

    @Deprecated
    protected String normalizeMimeType(String mimeType);
}

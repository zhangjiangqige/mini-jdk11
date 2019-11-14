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

    private static final long serialVersionUID = 8367026044764648243L;

    private static final Class<InputStream> ioInputStreamClass = InputStream.class;

    protected static final Class<?> tryToLoadClass(String className, ClassLoader fallback) throws ClassNotFoundException;

    private static DataFlavor createConstant(Class<?> rc, String prn);

    private static DataFlavor createConstant(String mt, String prn);

    private static DataFlavor initHtmlDataFlavor(String htmlFlavorType);

    public static final DataFlavor stringFlavor = createConstant(java.lang.String.class, "Unicode String");

    public static final DataFlavor imageFlavor = createConstant("image/x-java-image; class=java.awt.Image", "Image");

    @Deprecated
    public static final DataFlavor plainTextFlavor = createConstant("text/plain; charset=unicode; class=java.io.InputStream", "Plain Text");

    @Interned
    public static final String javaSerializedObjectMimeType = "application/x-java-serialized-object";

    public static final DataFlavor javaFileListFlavor = createConstant("application/x-java-file-list;class=java.util.List", null);

    @Interned
    public static final String javaJVMLocalObjectMimeType = "application/x-java-jvm-local-objectref";

    @Interned
    public static final String javaRemoteObjectMimeType = "application/x-java-remote-object";

    public static DataFlavor selectionHtmlFlavor = initHtmlDataFlavor("selection");

    public static DataFlavor fragmentHtmlFlavor = initHtmlDataFlavor("fragment");

    public static DataFlavor allHtmlFlavor = initHtmlDataFlavor("all");

    public DataFlavor() {
        super();
    }

    private DataFlavor(String primaryType, String subType, MimeTypeParameterList params, Class<?> representationClass, String humanPresentableName) {
        super();
        if (primaryType == null) {
            throw new NullPointerException("primaryType");
        }
        if (subType == null) {
            throw new NullPointerException("subType");
        }
        if (representationClass == null) {
            throw new NullPointerException("representationClass");
        }
        if (params == null)
            params = new MimeTypeParameterList();
        params.set("class", representationClass.getName());
        if (humanPresentableName == null) {
            humanPresentableName = params.get("humanPresentableName");
            if (humanPresentableName == null)
                humanPresentableName = primaryType + "/" + subType;
        }
        try {
            mimeType = new MimeType(primaryType, subType, params);
        } catch (MimeTypeParseException mtpe) {
            throw new IllegalArgumentException("MimeType Parse Exception: " + mtpe.getMessage());
        }
        this.representationClass = representationClass;
        this.humanPresentableName = humanPresentableName;
        mimeType.removeParameter("humanPresentableName");
    }

    public DataFlavor(Class<?> representationClass, String humanPresentableName) {
        this("application", "x-java-serialized-object", null, representationClass, humanPresentableName);
        if (representationClass == null) {
            throw new NullPointerException("representationClass");
        }
    }

    public DataFlavor(String mimeType, String humanPresentableName) {
        super();
        if (mimeType == null) {
            throw new NullPointerException("mimeType");
        }
        try {
            initialize(mimeType, humanPresentableName, this.getClass().getClassLoader());
        } catch (MimeTypeParseException mtpe) {
            throw new IllegalArgumentException("failed to parse:" + mimeType);
        } catch (ClassNotFoundException cnfe) {
            throw new IllegalArgumentException("can't find specified class: " + cnfe.getMessage());
        }
    }

    public DataFlavor(String mimeType, String humanPresentableName, ClassLoader classLoader) throws ClassNotFoundException {
        super();
        if (mimeType == null) {
            throw new NullPointerException("mimeType");
        }
        try {
            initialize(mimeType, humanPresentableName, classLoader);
        } catch (MimeTypeParseException mtpe) {
            throw new IllegalArgumentException("failed to parse:" + mimeType);
        }
    }

    public DataFlavor(String mimeType) throws ClassNotFoundException {
        super();
        if (mimeType == null) {
            throw new NullPointerException("mimeType");
        }
        try {
            initialize(mimeType, null, this.getClass().getClassLoader());
        } catch (MimeTypeParseException mtpe) {
            throw new IllegalArgumentException("failed to parse:" + mimeType);
        }
    }

    private void initialize(String mimeType, String humanPresentableName, ClassLoader classLoader) throws MimeTypeParseException, ClassNotFoundException;

    public String toString();

    private String paramString();

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

    private boolean isMimeTypeEqual(MimeType mtype);

    private boolean isStandardTextRepresentationClass();

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

    transient int atom;

    MimeType mimeType;

    private String humanPresentableName;

    private Class<?> representationClass;
}

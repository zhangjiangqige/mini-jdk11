package java.io;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.ObjectOutput;
import java.io.ObjectInput;

@AnnotatedFor({ "nullness" })
public interface Externalizable extends java.io.Serializable {

    void writeExternal(ObjectOutput out) throws IOException;

    void readExternal(ObjectInput in) throws IOException, ClassNotFoundException;
}

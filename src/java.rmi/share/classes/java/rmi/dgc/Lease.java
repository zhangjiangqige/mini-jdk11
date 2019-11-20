package java.rmi.dgc;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public final class Lease implements java.io.Serializable {

    public Lease(VMID id, long duration) {
    }

    public VMID getVMID();

    public long getValue();
}

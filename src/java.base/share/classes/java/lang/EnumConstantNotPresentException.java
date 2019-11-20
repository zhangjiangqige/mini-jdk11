package java.lang;

import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
@SuppressWarnings("rawtypes")
public class EnumConstantNotPresentException extends RuntimeException {

    @SideEffectFree
    public EnumConstantNotPresentException(Class<? extends Enum> enumType, String constantName) {
    }

    public Class<? extends Enum> enumType();

    public String constantName();
}

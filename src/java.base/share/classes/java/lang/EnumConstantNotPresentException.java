package java.lang;

import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "nullness" })
@SuppressWarnings("rawtypes")
public class EnumConstantNotPresentException extends RuntimeException {

    private static final long serialVersionUID = -6046998521960521108L;

    private Class<? extends Enum> enumType;

    private String constantName;

    @SideEffectFree
    public EnumConstantNotPresentException(Class<? extends Enum> enumType, String constantName) {
        super(enumType.getName() + "." + constantName);
        this.enumType = enumType;
        this.constantName = constantName;
    }

    public Class<? extends Enum> enumType();

    public String constantName();
}

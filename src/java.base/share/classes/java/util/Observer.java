package java.util;

import org.checkerframework.framework.qual.AnnotatedFor;
import org.checkerframework.framework.qual.CFComment;

@CFComment({ "guieffect: These types are polymorphic, but they're basically unusable unless I fix the subtyping among differently permissioned interfaces...", "package java.util;", "@PolyUIType interface Observer {", "@PolyUIEffect void update(@PolyUI Observer this, @PolyUI Observable o, Object arg);" })
@AnnotatedFor({ "lock", "nullness" })
@Deprecated(since = "9")
public interface Observer {

    void update(Observable o, Object arg);
}

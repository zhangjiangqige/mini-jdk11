package javax.script;

import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.io.Reader;
import java.util.Map;
import java.util.Set;

@AnnotatedFor({ "interning" })
public interface ScriptEngine {

    @Interned
    public static final String ARGV;

    @Interned
    public static final String FILENAME;

    @Interned
    public static final String ENGINE;

    @Interned
    public static final String ENGINE_VERSION;

    @Interned
    public static final String NAME;

    @Interned
    public static final String LANGUAGE;

    @Interned
    public static final String LANGUAGE_VERSION;

    public Object eval(String script, ScriptContext context) throws ScriptException;

    public Object eval(Reader reader, ScriptContext context) throws ScriptException;

    public Object eval(String script) throws ScriptException;

    public Object eval(Reader reader) throws ScriptException;

    public Object eval(String script, Bindings n) throws ScriptException;

    public Object eval(Reader reader, Bindings n) throws ScriptException;

    public void put(String key, Object value);

    public Object get(String key);

    public Bindings getBindings(int scope);

    public void setBindings(Bindings bindings, int scope);

    public Bindings createBindings();

    public ScriptContext getContext();

    public void setContext(ScriptContext context);

    public ScriptEngineFactory getFactory();
}

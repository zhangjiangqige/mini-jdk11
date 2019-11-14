package javax.xml.parsers;

import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor("nullness")
public class ParserConfigurationException extends Exception {

    private static final long serialVersionUID = -3688849216575373917L;

    public ParserConfigurationException() {
        super();
    }

    public ParserConfigurationException(String msg) {
        super(msg);
    }
}

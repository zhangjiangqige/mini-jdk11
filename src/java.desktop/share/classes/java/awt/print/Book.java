package java.awt.print;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Vector;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Book implements Pageable {

    public Book() {
    }

    public int getNumberOfPages();

    public PageFormat getPageFormat(int pageIndex) throws IndexOutOfBoundsException;

    public Printable getPrintable(int pageIndex) throws IndexOutOfBoundsException;

    public void setPage(int pageIndex, Printable painter, PageFormat page) throws IndexOutOfBoundsException;

    public void append(Printable painter, PageFormat page);

    public void append(Printable painter, PageFormat page, int numPages);
}

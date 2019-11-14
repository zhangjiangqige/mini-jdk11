package java.awt.print;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.util.Vector;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public class Book implements Pageable {

    private Vector<BookPage> mPages;

    public Book() {
        mPages = new Vector<>();
    }

    public int getNumberOfPages();

    public PageFormat getPageFormat(int pageIndex) throws IndexOutOfBoundsException;

    public Printable getPrintable(int pageIndex) throws IndexOutOfBoundsException;

    public void setPage(int pageIndex, Printable painter, PageFormat page) throws IndexOutOfBoundsException;

    public void append(Printable painter, PageFormat page);

    public void append(Printable painter, PageFormat page, int numPages);

    private BookPage getPage(int pageIndex) throws ArrayIndexOutOfBoundsException;

    private class BookPage {

        private PageFormat mFormat;

        private Printable mPainter;

        BookPage(Printable painter, PageFormat format) {
            if (painter == null || format == null) {
                throw new NullPointerException();
            }
            mFormat = format;
            mPainter = painter;
        }

        Printable getPrintable();

        PageFormat getPageFormat();
    }
}

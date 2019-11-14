package java.awt.print;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import java.awt.AWTError;
import java.awt.HeadlessException;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.StreamPrintServiceFactory;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
public abstract class PrinterJob {

    public static PrinterJob getPrinterJob();

    public static PrintService[] lookupPrintServices();

    public static StreamPrintServiceFactory[] lookupStreamPrintServices(String mimeType);

    public PrinterJob() {
    }

    public PrintService getPrintService();

    public void setPrintService(PrintService service) throws PrinterException;

    public abstract void setPrintable(Printable painter);

    public abstract void setPrintable(Printable painter, PageFormat format);

    public abstract void setPageable(Pageable document) throws NullPointerException;

    public abstract boolean printDialog() throws HeadlessException;

    public boolean printDialog(PrintRequestAttributeSet attributes) throws HeadlessException;

    public abstract PageFormat pageDialog(PageFormat page) throws HeadlessException;

    public PageFormat pageDialog(PrintRequestAttributeSet attributes) throws HeadlessException;

    public abstract PageFormat defaultPage(PageFormat page);

    public PageFormat defaultPage();

    public PageFormat getPageFormat(PrintRequestAttributeSet attributes);

    public abstract PageFormat validatePage(PageFormat page);

    public abstract void print() throws PrinterException;

    public void print(PrintRequestAttributeSet attributes) throws PrinterException;

    public abstract void setCopies(int copies);

    public abstract int getCopies();

    public abstract String getUserName();

    public abstract void setJobName(String jobName);

    public abstract String getJobName();

    public abstract void cancel();

    public abstract boolean isCancelled();
}

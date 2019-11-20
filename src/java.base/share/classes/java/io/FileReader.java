package java.io;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.charset.Charset;

@AnnotatedFor({ "nullness" })
public class FileReader extends InputStreamReader {

    public FileReader(String fileName) throws FileNotFoundException {
    }

    public FileReader(File file) throws FileNotFoundException {
    }

    public FileReader(FileDescriptor fd) {
    }

    public FileReader(String fileName, Charset charset) throws IOException {
    }

    public FileReader(File file, Charset charset) throws IOException {
    }
}

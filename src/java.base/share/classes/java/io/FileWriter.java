package java.io;

import org.checkerframework.framework.qual.AnnotatedFor;
import java.nio.charset.Charset;

@AnnotatedFor({ "nullness" })
public class FileWriter extends OutputStreamWriter {

    public FileWriter(String fileName) throws IOException {
    }

    public FileWriter(String fileName, boolean append) throws IOException {
    }

    public FileWriter(File file) throws IOException {
    }

    public FileWriter(File file, boolean append) throws IOException {
    }

    public FileWriter(FileDescriptor fd) {
    }

    public FileWriter(String fileName, Charset charset) throws IOException {
    }

    public FileWriter(String fileName, Charset charset, boolean append) throws IOException {
    }

    public FileWriter(File file, Charset charset) throws IOException {
    }

    public FileWriter(File file, Charset charset, boolean append) throws IOException {
    }
}

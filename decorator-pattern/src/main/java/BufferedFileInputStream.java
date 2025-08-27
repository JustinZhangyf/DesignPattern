import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 缓冲文件输入流
 * 继承FileInputStream
 * 添加缓冲功能
 * 缓冲区大小为8192字节
 * 鉴于每次读取文件时，磁盘I/O操作会比较耗时，因此，将多个字节数据写入缓冲区，使用流读取是只需要从缓冲区读取，减少磁盘I/O操作，从而提高性能
 */
public class BufferedFileInputStream extends InputStream {

    private int[] buffer = new int[8192];
    //    用于记录缓冲区中已经读取的字节数
    private int pos = -1;
    //    用于记录缓冲区中可以读取的字节数
    private int capacity = -1;

    private InputStream inputStream;

    public BufferedFileInputStream(InputStream inputStream) throws IOException {
        this.inputStream = inputStream;
    }


    @Override
    public int read() throws IOException {
        // check buffer中是否可以读取字节，如果可以，则从缓冲区中读取字节，否则，从输入流中读取字节并添加到缓冲区中
        if (bufferCanBeRead()) {
            return readFromBuffer();
        }
        refreshBuffer();
        if (!bufferCanBeRead()) {
            return -1;
        }
        return readFromBuffer();
    }

    private void refreshBuffer() {

    }

    private int readFromBuffer() {
        return 0;
    }

    private boolean bufferCanBeRead() {
    }

    @Override
    public void close() throws IOException {
        super.close();
    }
}

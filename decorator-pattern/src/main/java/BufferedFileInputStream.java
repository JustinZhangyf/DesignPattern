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

    private byte[] buffer = new byte[8192];
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

    private void refreshBuffer() throws IOException {
        this.capacity = this.inputStream.read(buffer);
        this.pos = 0;
    }

    private int readFromBuffer() {
        int b = buffer[pos];
        pos++;
//        buffer是字节数组，有符号位，因此需要将其转换为无符号位
        return b & 0xFF;
    }

    private boolean bufferCanBeRead() {
        if (capacity == -1) {
            return false;
        }
        // 缓冲区中已经读取的字节数等于缓冲区中可以读取的字节数，则缓冲区中已经没有字节可以读取，返回false
        if (pos == capacity) {
            return false;
        }
        return true;
    }

    @Override
    public void close() throws IOException {
        super.close();
    }
}

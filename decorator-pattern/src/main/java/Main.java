import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        HistorySet<String> strSet = new HistorySet<>(new HashSet<>());

        strSet.add("James");
        strSet.add("Tom");
        strSet.add("Alice");
        strSet.add("Bob");

        strSet.remove("Tom");
        strSet.remove("Justin");
        strSet.remove("Alice");

        System.out.println(strSet);

        Set<String> synchronizedSet = Collections.synchronizedSet(strSet);

        // 下方代码块并不是线程安全的，因为isEmpty和add两个操作不是原子操作
        if (synchronizedSet.isEmpty()) {
            synchronizedSet.add("Tommy");
        }

//        byte num = -128;
//        byte[] bytes = new byte[256];
//
//        for (int i = 0; i < bytes.length; i++) {
//            bytes[i] = num;
//            num++;
//        }
//
//        System.out.println(Arrays.toString(bytes));
//
//        for (int i = 0; i < bytes.length; i++) {
//            int unsignedByte = bytes[i] & 0xFF;
//            System.out.println("Signed: " + bytes[i] + " -> Unsigned: " + unsignedByte);
//        }

        readFile();
    }

    // 理解Java IO流的装饰器模式
    public static void readFile() {
        File file = new File("decorator-pattern/jvm.pdf");

        // multi thread read file, one thread read file using FileInputStream, another thread read file using BufferedFileInputStream
        Thread thread1 = new Thread(() -> {
            long epochMilli = Instant.now().toEpochMilli();
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                while (true) {
                    int read = fileInputStream.read();
                    if (read == -1) {
                        break;
                    }
                }
                System.out.println(Thread.currentThread().getName() + "Read time: " + (Instant.now().toEpochMilli() - epochMilli) + "ms");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            long epochMilli = Instant.now().toEpochMilli();

            try (BufferedFileInputStream bufferedFileInputStream = new BufferedFileInputStream(new FileInputStream(file));
                 FileInputStream fileInputStream = new FileInputStream(file)) {
                System.out.println("bufferedFileInputStream and fileInputStream are reading the same file");
                while (true) {
                    int bufferRead = bufferedFileInputStream.read();
                    int read = fileInputStream.read();

                    if (bufferRead != read) {
                        throw new RuntimeException("bufferRead != read");
                    }
                    if (bufferRead == -1) {
                        break;
                    }
                }
                System.out.println(Thread.currentThread().getName() + "Read time: " + (Instant.now().toEpochMilli() - epochMilli) + "ms");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
//        thread1.start();
        thread2.start();

    }
}

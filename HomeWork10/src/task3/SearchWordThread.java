package task3;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class SearchWordThread extends Thread {
    private CountDownLatch latch;
    private String directoryPath, mergedFilePath, searchWord;
    private AtomicInteger fileCount, dirCount;

    public SearchWordThread(){
        this.latch = Main.latch;
        this.searchWord = Main.searchWord;
        this.mergedFilePath = Main.mergedFilePath;
        this.directoryPath = Main.directoryPath;
        this.fileCount = Main.fileCount;
        this.dirCount = Main.dirCount;
    }
    @Override
    public void run(){
        // They are autocloseable
        try{
            RandomAccessFile reader = new RandomAccessFile(mergedFilePath, "rw");
            FileChannel fileChannel = reader.getChannel();

            Charset charset = StandardCharsets.UTF_8;

            Files.walk(Paths.get(directoryPath))
                    .forEach(source -> {
                        System.out.println(source);
                        if (Files.isDirectory(source)) {
                            dirCount.incrementAndGet();
                        }
                        else { // file
                            fileCount.incrementAndGet();
                            try {
                                // 2 раза здесь считываются данные из source, один раз проверка, другой копирование
                                Stream<String> stream = Files.lines(source, StandardCharsets.UTF_8);
                                // if contains searchWord
                                if (stream.anyMatch(line -> line.contains(searchWord))) {
                                    // copy line by line into fileChannel using charset
                                    Files.lines(source, StandardCharsets.UTF_8)
                                            .forEach(line -> {
                                                try {
                                                    fileChannel.write(charset.encode(line + System.lineSeparator()));
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            });
                                }
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            latch.countDown();
        }
    }
}
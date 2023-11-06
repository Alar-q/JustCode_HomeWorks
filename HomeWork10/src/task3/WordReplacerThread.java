package task3;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class WordReplacerThread extends Thread {
    private CountDownLatch latch;
    private String mergedFilePath;
    private List<String> bannedWords;
    public WordReplacerThread(){
        this.latch = Main.latch;
        this.bannedWords = Main.bannedWords;
        this.mergedFilePath = Main.mergedFilePath;
    }
    @Override
    public void run(){
        try {
            latch.await(); // Ждем завершения первого потока

            Path path = Paths.get(mergedFilePath);
            Charset charset = StandardCharsets.UTF_8;

            List<String> lines = Files.lines(path, charset)
                    .map(line -> removeWordIgnoreCase(line, bannedWords))
                    .toList();

            Files.write(path, lines);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String removeWordIgnoreCase(String line, List<String> wordsToRemove) {
        // Разбиваем строку на слова
        String[] words = line.split(" ");

        StringBuilder result = new StringBuilder();

        for (String word : words) {
            String _word = word.replaceAll("[\\p{Punct}]", "").toLowerCase();

            // Проверяем, совпадает ли слово с искомым словом в независимости от регистра
            if (result.length() > 0) {
                result.append(" ");
            }
            if (wordsToRemove.stream().anyMatch(w -> _word.equals(w.toLowerCase()))) {
                result.append("***");
            } else {
                result.append(word);
            }
        }

        // Возвращаем получившуюся строку
        return result.toString();
    }

}

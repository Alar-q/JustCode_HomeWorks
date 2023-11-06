import java.util.Arrays;
import java.util.regex.Pattern;

public class Main{
    public static void main(String[] args){
        String word = "блин";
        String line = "Тут есть Блин, блин и даже БЛИН!";

        System.out.println(line);
        System.out.println(removeWordIgnoreCase(line, word));
    }

    public static String removeWordIgnoreCase(String line, String wordToRemove) {
        // Разбиваем строку на слова
        String[] words = line.split(" ");

        StringBuilder result = new StringBuilder();

        String _wordToRemove = wordToRemove.toLowerCase();

        for (String word : words) {
            String _word = word.replaceAll("[\\p{Punct}]", "").toLowerCase();

            // Проверяем, совпадает ли слово с искомым словом в независимости от регистра
            if (result.length() > 0) {
                result.append(" ");
            }
            if (_word.equals(_wordToRemove)) {
                result.append("***");
            } else {
                result.append(word);
            }
        }

        // Возвращаем получившуюся строку
        return result.toString();
    }

}
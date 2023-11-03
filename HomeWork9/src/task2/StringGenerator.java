package task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class StringGenerator implements Callable<List<String>> {
    public static int MAX_LENGTH = 100;
    private int number;
    public StringGenerator(int number){
        this.number = number;
    }
    @Override
    public List<String> call() throws Exception {
        List<String> list = new ArrayList<>();
        for(int i=0; i<number; i++){
            list.add(randomString());
        }
        return list;
    }
    //[33; 127) - все символы
    //[97; 123) - англ латиница маленькие буквы
    public static char randomChar(){
        return (char) (Math.random() * (123 - 97) + 97);
    }
    public static String randomString(){
        int length = (int) (Math.random() * MAX_LENGTH);
        char[] string = new char[length];
        for(int i=0; i<length; i++){
            string[i] = randomChar();
        }
        return String.valueOf(string);
    }
}

import java.util.Scanner;

public class First_Calendar {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите дату рождения (день месяц год):");
        int day = scanner.nextInt();
        String month = scanner.next();
        int year = scanner.nextInt();

        String zodiacSign = getZodiacSign(day, month);
        String japaneseYear = getJapaneseYear(year);

        System.out.println("Знак: " + zodiacSign + " Год: " + japaneseYear);
    }

    public static String getZodiacSign(int day, String month) {
        if(day <= 0 || day >= 31){
            return "Неверный день";
        }

        String[] signs = {
                "козерог", "водолей", "рыбы", "овен", "телец", "близнецы",
                "рак", "лев", "дева", "весы", "скорпион", "стрелец"
        };

        // Последние дни каждого знака зодиака
        int[] days = {20, 19, 20, 20, 21, 21, 22, 21, 23, 23, 22, 22};

        int monthIndex = monthToIndex(month);
        if (monthIndex == -1) {
            return "Неверный месяц";
        }

        if (day > days[monthIndex]) {
            monthIndex = monthIndex + 1;
            if (monthIndex == 12) {
                monthIndex = 0;
            }
        }

        return signs[monthIndex];
    }

    public static int monthToIndex(String month) {
        String[] months = {
                "января", "февраля", "марта", "апреля", "мая", "июня",
                "июля", "августа", "сентября", "октября", "ноября", "декабря"
        };
        for (int i = 0; i < months.length; i++) {
            if (months[i].equalsIgnoreCase(month)) {
                return i;
            }
        }
        return -1;
    }

    public static String getJapaneseYear(int year) {
        String[] japaneseYears = {
                "крысы", "коровы", "тигра", "кролика", "дракона", "змеи",
                "лошади", "овцы", "обезьяны", "петуха", "собаки", "свиньи"
        };

        int index = (year - 1924) % 12;
        return japaneseYears[index];
    }
}

import java.util.Random;

public class Rand {
//    private static Random random = new Random();
    /**
     * Метод дающий экспоненциальное случайное распределение.
     * Может выдать значение в диапазоне [0; inf].
     * В основном будут падать значения меньше mean.
     * До 0.1 (10%) вероятность выбросить большое число растет логарифмически.
     *
     * В результате может выйти бесконечность(большое число) и пассажир никогда не придет,
     * но вероятность этого очень мала ln(0.0001)=-9.21 или меньше чем 0.0001 (0.01%) у нас выпадет слишком большое число.
     *
     * В нашей задаче хотелось бы сделать отклонение поменьше.
     * */
    public static double expRandom(double mean){
        // Domain of Math.log(Math.random()) = [-inf, 0]
        return mean * -Math.log(Math.random());
//        return -Math.log(1 - random.nextDouble()) * mean;
    }

    public static boolean luck(double probability){
        return Math.random() < probability;
    }

    public static double randomRange(double from, double to){
        // Не стоит выкидывать RuntimeException
        if(from > to){throw new RuntimeException("from can't be greater than to");}
        return (Math.random() * (to - from)) + from;
    }
}

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class FerryTerminalSimulation {

    private static final int N = 10;
    private static final double MEAN_ARRIVAL_TIME = 5;
    private static final double MEAN_FERRY_TIME = 10.0;
    private static final int MAX_SEATS = 30;
    private static final boolean IS_LAST_STOP = false;

    public static void main(String[] args) {
        Random random = new Random();

        Queue<Double> events = new PriorityQueue<>();
        Queue<Double> passengersQueue = new LinkedList<>();

        double totalWaitingTime = 0;
        int totalPassengers = 0;

        double currentTime = 0;
        double nextArrivalTime = currentTime + -Math.log(1 - random.nextDouble()) * MEAN_ARRIVAL_TIME;
        double nextFerryTime = currentTime + MEAN_FERRY_TIME;

        events.add(nextArrivalTime);
        events.add(nextFerryTime);

        while (totalPassengers < 1000) { // имитация для 1000 пассажиров
            currentTime = events.poll();

            if (currentTime == nextArrivalTime) {
                passengersQueue.add(currentTime);
                nextArrivalTime = currentTime + -Math.log(1 - random.nextDouble()) * MEAN_ARRIVAL_TIME;
                events.add(nextArrivalTime);
            } else if (currentTime == nextFerryTime) {
                int passengersInFerry = 0;
                while (!passengersQueue.isEmpty() && passengersQueue.peek() <= currentTime &&
                        (IS_LAST_STOP || passengersInFerry < MAX_SEATS)) {
                    double waitingTime = currentTime - passengersQueue.poll();
                    totalWaitingTime += waitingTime;
                    totalPassengers++;
                    passengersInFerry++;
                }
                nextFerryTime = currentTime + MEAN_FERRY_TIME;
                events.add(nextFerryTime);
            }
        }

        double averageWaitingTime = totalWaitingTime / totalPassengers;
        double sufficientInterval = MEAN_ARRIVAL_TIME * N;

        System.out.println("Среднее время пребывания человека на остановке: " + averageWaitingTime);
        System.out.println("Достаточный интервал времени между приходами катеров: " + sufficientInterval);

        int freeSeats = MAX_SEATS - (int) (random.nextDouble() * MAX_SEATS);
        System.out.println("Количество свободных мест в катере: " + freeSeats);
    }
}

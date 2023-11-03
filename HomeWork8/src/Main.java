public class Main {
    public static final int N = 10;
    public static final double MEAN_PERSON_TIME = 3;
    public static double MEAN_FERRY_TIME = 30;
    public static final int MAX_SEATS = 20;
    public static final double IS_LAST_PROBABILITY = 0.1d;


    public static void main(String[] args) {
        Counter counter = new Counter();

        Events events = new Events();

        double currentTime = 0;

        events.scheduleNextPerson(currentTime);
        events.scheduleNextFerry(currentTime);

        // Симуляция продолжается, пока не будет обслужено 1000000 пассажиров
        while (counter.totalPassengers() < 10) {
            System.out.println();
            System.out.println(events);

            currentTime = events.next();

            // Если текущее событие - прибытие пассажира
            // personsQueue не может быть пустым
            if (events.isPersonArrived(currentTime)) {
                events.scheduleNextPerson(currentTime);
                counter.newPersonArrived(currentTime);
            }
            // Если текущее событие - прибытие парома
            else if (events.isFerryArrived(currentTime)) {
                // Среднее
                events.passengerOnboarding(currentTime, counter);

                events.scheduleNextFerry(currentTime);

                if (events.isTookAllPassengers()) {
                    events.scheduleNextPerson(currentTime);
                }
            }
            else {
                throw new RuntimeException("Something went wrong!");
            }
        }

        System.out.println();

        // Расчет и вывод среднего времени ожидания и других параметров
        double averageWaitingTime = counter.totalWaitingTime() / counter.totalPassengers();

        System.out.println("Среднее время пребывания человека на остановке: " + averageWaitingTime);
        System.out.println("Среднее количество человек на остановке: " + counter.meanNumberOfPassengersOnPlatform());

        double meanFerryCapacity = counter.meanFerryCapacity();
        double meanTimePersonArrives = counter.meanTimePersonArrives();
        System.out.println("meanFerryCapacity: " + meanFerryCapacity);
        System.out.println("meanTimePersonArrives: " + meanTimePersonArrives);

        double sufficientInterval = (meanTimePersonArrives * (meanFerryCapacity));
        System.out.println("Main: Достаточный интервал времени между приходами катеров: " + sufficientInterval);

        MEAN_FERRY_TIME = sufficientInterval;

        for(int i=0; i<10; i++){
            sufficientInterval = step(MEAN_FERRY_TIME);
            MEAN_FERRY_TIME = sufficientInterval;
            System.out.println("Step: Достаточный интервал времени между приходами катеров: " + sufficientInterval);
        }
    }


    public static double step(double sufficientInterval){
        Counter counter = new Counter();

        Events events = new Events();

        double currentTime = 0;

        events.scheduleNextPerson(currentTime);
        events.scheduleNextFerry(currentTime);

        // Симуляция продолжается, пока не будет обслужено 1000000 пассажиров
        while (counter.totalPassengers() < 10000) {
//            System.out.println();
//            System.out.println(events);

            currentTime = events.next();

            // Если текущее событие - прибытие пассажира
            // personsQueue не может быть пустым
            if (events.isPersonArrived(currentTime)) {
                events.scheduleNextPerson(currentTime);
                counter.newPersonArrived(currentTime);
            }
            // Если текущее событие - прибытие парома
            else if (events.isFerryArrived(currentTime)) {
                // Среднее
                events.passengerOnboarding(currentTime, counter);

                events.scheduleNextFerry(currentTime);

                if (events.isTookAllPassengers()) {
                    events.scheduleNextPerson(currentTime);
                }
            }
            else {
                throw new RuntimeException("Something went wrong!");
            }
        }

        System.out.println();

        // Расчет и вывод среднего времени ожидания и других параметров
        double averageWaitingTime = counter.totalWaitingTime() / counter.totalPassengers();
        double meanNumberOfPassengersOnPlatform = counter.meanNumberOfPassengersOnPlatform();

        System.out.println("Среднее время пребывания человека на остановке: " + averageWaitingTime);
        System.out.println("Среднее количество человек на остановке: " + meanNumberOfPassengersOnPlatform);

        double meanFerryCapacity = counter.meanFerryCapacity();
        double meanTimePersonArrives = counter.meanTimePersonArrives();
        System.out.println("meanFerryCapacity: " + meanFerryCapacity);
        System.out.println("meanTimePersonArrives: " + meanTimePersonArrives);

//        double sufficientInterval = (meanTimePersonArrives * (meanFerryCapacity - 1));
//        System.out.println("Достаточный интервал времени между приходами катеров: " + (meanTimePersonArrives * (meanFerryCapacity - 1)));

        if(meanNumberOfPassengersOnPlatform > meanFerryCapacity){
            return sufficientInterval - 0.05 * sufficientInterval;
        }
        else if(meanNumberOfPassengersOnPlatform < meanFerryCapacity - meanFerryCapacity/4){
            return sufficientInterval + 0.05 * sufficientInterval;
        }
        return sufficientInterval;
    }
}
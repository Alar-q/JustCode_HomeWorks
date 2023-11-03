import java.util.*;

public class Events {
    private Queue<Double> events;
    private LinkedList<Person> personsQueue;
    private LinkedList<Ferry> ferriesQueue;

    public Events(){
        this.events = new PriorityQueue<>();
        this.personsQueue = new LinkedList<>();
        this.ferriesQueue = new LinkedList<>();
    }
    public Person scheduleNextPerson(double currentTime){
        Person person = new Person(currentTime);
        personsQueue.add(person);
        events.add(person.arrivalTime());

//        System.out.println("Запланирован пассажир: " + person);
        return person;
    }

    public void scheduleNextFerry(double currentTime){
        Ferry ferry = new Ferry(currentTime);
        ferriesQueue.add(ferry);
        events.add(ferry.arrivalTime());

//        System.out.println("Запланирован паром: " + ferry);
    }

    public double next(){
        return events.poll();
    }

    public boolean isPersonArrived(double currentTime){
        return currentTime == personsQueue.peekLast().arrivalTime();
    }
    public boolean isFerryArrived(double currentTime){
        return currentTime == ferriesQueue.peekLast().arrivalTime();
    }

    public void passengerOnboarding(double currentTime, Counter counter){
        counter.add2NumberOfPassengersOnPlatform(personsQueue.size());

        Ferry ferry = ferriesQueue.poll();

        if(currentTime != ferry.arrivalTime()){
            throw new RuntimeException("Something wrong!");
        }

        counter.addFerryCapacity(ferry.seatsNum());

        if(ferry.isLastStop()){
//            System.out.println("Паром на конечной остановке");
            return;
        }


        // Загрузка пассажиров в паром, если это возможно
        while (!personsQueue.isEmpty() && personsQueue.peek().arrivalTime() <= currentTime
                && ferry.isSeatsAvailable()) {
            ferry.takeASeat();
//            System.out.println("ferry.passengersIn: " + ferry.passengersIn() + "Persons Queue(" + personsQueue.size() + ")");

            double dWaitingTime = currentTime - personsQueue.poll().arrivalTime();
            counter.add2TotalWaitingTime(dWaitingTime);

            counter.add2TotalPassengers(1);
        }

//        System.out.println("На платформе " + personsQueue.size());
    }

    public boolean isTookAllPassengers(){
        return personsQueue.isEmpty();
    }

    @Override
    public String toString(){
        return String.format("Events(%d): %s\n" +
                "Persons Queue(%d): %s\n" +
                "Ferries Queue(%d): %s",
                events.size(), events.toString(), personsQueue.size(), personsQueue.toString(), ferriesQueue.size(), ferriesQueue.toString());
    }
}

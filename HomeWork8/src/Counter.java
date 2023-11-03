import java.util.LinkedList;
import java.util.List;

public class Counter {
    private double totalWaitingTime;
    private int totalPassengers;
    private int numberOfPassengersOnPlatform = 0;
    private int numberOfPassengersOnPlatformCounting = 0;

    private List<Double> frequencyOfPassengers;
    private List<Integer> ferryCapacity;

    public Counter(){
        this.totalWaitingTime = 0;
        this.totalPassengers = 0;
        this.frequencyOfPassengers = new LinkedList<>();
        this.ferryCapacity = new LinkedList<>();
    }
    public double totalWaitingTime(){
        return this.totalWaitingTime;
    }
    public int totalPassengers(){
        return this.totalPassengers;
    }

    public void add2TotalWaitingTime(double val){
        this.totalWaitingTime += val;
    }
    public void add2TotalPassengers(int val){
        this.totalPassengers += val;
    }

    public void newPersonArrived(double currentTime){
        frequencyOfPassengers.add(currentTime);
    }
    public void addFerryCapacity(int seats){
        ferryCapacity.add(seats);
    }

    public double meanFerryCapacity(){
        if (ferryCapacity.isEmpty()) {
            return 0; // To avoid division by zero
        }

        int totalCapacity = 0;
        for (int capacity : ferryCapacity) {
            totalCapacity += capacity;
        }

        return (double) totalCapacity / ferryCapacity.size();
    }
    public double meanTimePersonArrives(){
        if (frequencyOfPassengers.size() < 2) {
            return 0; // Not enough data to calculate mean time between arrivals
        }

        double totalTimeBetweenArrivals = 0;
        for (int i = 1; i < frequencyOfPassengers.size(); i++) {
            totalTimeBetweenArrivals += frequencyOfPassengers.get(i) - frequencyOfPassengers.get(i-1);
        }

        return totalTimeBetweenArrivals / (frequencyOfPassengers.size() - 1);
    }

    public void add2NumberOfPassengersOnPlatform(int val){
        this.numberOfPassengersOnPlatform += val;
        numberOfPassengersOnPlatformCounting++;
    }
    public int meanNumberOfPassengersOnPlatform(){
        return numberOfPassengersOnPlatform / numberOfPassengersOnPlatformCounting;
    }

}

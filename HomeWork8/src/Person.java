public class Person implements Scheduled{
    private int id;
    private double arrivalTime;

    public Person(double currentTime){
        this.id = (int)(Math.random() * 1000000); // just random
        this.arrivalTime = currentTime + Rand.expRandom(Main.MEAN_PERSON_TIME);
//        this.arrivalTime = currentTime + Main.MEAN_PERSON_TIME;
    }

    public Person(double currentTime, boolean randomnessOff){
        this.id = (int)(Math.random() * 1000000); // just random
//        this.arrivalTime = currentTime + Rand.expRandom(Main.MEAN_PERSON_TIME);
        this.arrivalTime = currentTime + Main.MEAN_PERSON_TIME;
    }

    @Override
    public double arrivalTime(){
        return this.arrivalTime;
    }

    @Override
    public String toString(){
//        return String.format("arrivalTime(%d), id(%d)", (int)arrivalTime, id);
        return String.format("arrivalTime(%f), id(%d)", arrivalTime, id);
    }
}

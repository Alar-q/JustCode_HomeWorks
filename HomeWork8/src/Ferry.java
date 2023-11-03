public class Ferry implements Scheduled {
    private double arrivalTime;
    private boolean isLastStop;
    private int seatsNum;
    // Неизменяемое изначальное значение
    private final int _seatsNum;

    public Ferry(double currentTime, boolean randomnessOff){
        this.arrivalTime = currentTime + Main.MEAN_FERRY_TIME;
        this.isLastStop = Rand.luck(Main.IS_LAST_PROBABILITY);
        this.seatsNum = Main.MAX_SEATS / 2;
//        this.seatsNum = (int) Rand.randomRange(1, Main.MAX_SEATS); // 1 - MAX_SEATS
        this.seatsNum = isLastStop ? 0 : this.seatsNum;
        this._seatsNum = this.seatsNum;
    }
    public Ferry(double currentTime){
        this.arrivalTime = currentTime + Main.MEAN_FERRY_TIME;
        this.isLastStop = Rand.luck(Main.IS_LAST_PROBABILITY);
        this.seatsNum = (int) Rand.randomRange(1, Main.MAX_SEATS); // 1 - MAX_SEATS
        this.seatsNum = isLastStop ? 0 : this.seatsNum;
        this._seatsNum = this.seatsNum;
    }

    @Override
    public double arrivalTime(){
        return this.arrivalTime;
    }

    public boolean isLastStop(){
        return this.isLastStop;
    }
    public int seatsNum(){
        return this.seatsNum;
    }

    public boolean isSeatsAvailable(){
        return seatsNum > 0;
    }

    public boolean takeASeat(){
        seatsNum--;
        return seatsNum >= 0;
    }

    public int passengersIn(){
        return _seatsNum - seatsNum;
    }

    @Override
    public String toString(){
//        return String.format("arrivalTime(%d), isLastStop(%b) seatsNum(%d)", (int)arrivalTime, isLastStop, seatsNum);
        return String.format("arrivalTime(%f), isLastStop(%b) seatsNum(%d)", arrivalTime, isLastStop, seatsNum);
    }
}

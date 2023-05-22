public class Process {
    final int load;
    int arrivalTime;

    public Process(int load, int arrivalTime) {
        this.load = load;
        this.arrivalTime = arrivalTime;
    }

    public int getLoad() {
        return load;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public String toString() {
        return "Process{" +
                "load=" + load +
                ", arrivalTime=" + arrivalTime +
                '}';
    }

    public void increaseArrivalTimeBy(int time) {
        arrivalTime += time;
    }

    @Override
    public Process clone() {
        return new Process(this.load, this.arrivalTime);
    }
}
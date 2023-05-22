import java.util.Arrays;

public class Process {
    private int size;
    private int[] calls;
    private int causedPFNum;

    public Process(int[] calls, int size){
        this.calls = calls;
        this.size = size;
        causedPFNum = 0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[] getCalls() {
        return calls;
    }

    public void setCalls(int[] calls) {
        this.calls = calls;
    }

    public void incrementPF(){
        causedPFNum++;
    }

    public void decrementPF(){
        causedPFNum--;
    }

    public int getCausedPFNum() {
        return causedPFNum;
    }

    @Override
    public String toString() {
        return "Process{" +
                "size=" + size +
                ", calls=" + Arrays.toString(calls) +
                '}';
    }
}

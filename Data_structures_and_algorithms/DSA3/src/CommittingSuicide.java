public class CommittingSuicide {
    EfficientQueue<Integer> people = new EfficientQueue<>();
    private final int killingEveryNth;

    public CommittingSuicide(int peopleNumber, int killingEveryNth) {

        for (int i = 1; i <= peopleNumber; i++) {
            people.enqueue(i);
        }

        this.killingEveryNth = killingEveryNth;
    }

    public int killPeople() {
        int survivor = -1;
        int counter = 0;

        while (!people.isEmpty()) {

            if (counter+1 == killingEveryNth) {
                survivor = people.dequeue();
                counter = 0;
            } else {
                people.enqueue(people.dequeue());
                counter++;
            }
        }

        return survivor;
    }
}

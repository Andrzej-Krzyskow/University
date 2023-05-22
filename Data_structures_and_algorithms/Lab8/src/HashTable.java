import java.util.LinkedList;

public class HashTable {
    LinkedList<Object>[] arr;
    private final static int defaultInitSize = 8;
    private final static double defaultMaxLoadFactor = 0.7;
    private int size;
    private int elementsCounter = 0;
    private final double maxLoadFactor;


    public HashTable() {
        this(defaultInitSize);
    }

    public HashTable(int size) {
        this(size, defaultMaxLoadFactor);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int initCapacity, double maxLF) {

        size = initCapacity;

        if (initCapacity < 1) {
            size = defaultInitSize;
        }


        arr = new LinkedList[size];

        this.maxLoadFactor = maxLF;
    }




    public boolean add(Object elem) {

        if (this.get(elem) != null) {
            return false;
        }

        if ((elementsCounter * 1.0+1) / size > maxLoadFactor) {
            doubleArray();
        }

        int hashCode = elem.hashCode() % size;

        if (arr[hashCode] == null) {
            arr[hashCode] = new LinkedList<>();
        }

        arr[hashCode].add(elem);
        elementsCounter++;

        return true;
    }

    @SuppressWarnings("unchecked")
    private void doubleArray() {
        LinkedList<Object>[] tmpArr = arr;

        size *= 2;
        elementsCounter = 0;
        arr = new LinkedList[size];

        for (int i = 0; i < size/2; i++) {
            if (tmpArr[i] != null) {
                for (Object object : tmpArr[i]) {
                    this.add(object);
                }
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            stringBuilder.append(i).append(":");

            if (arr[i] != null) {

                if (!arr[i].isEmpty()) {
                    stringBuilder.append(" ");
                }

                for (Object object : arr[i]) {
                    stringBuilder.append(((IWithName) object).getName()).append(", ");
                }

                if (!arr[i].isEmpty()) {
                    stringBuilder.setLength(stringBuilder.length() - 2);
                }
            }

            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public Object get(Object toFind) {

        int hashCode = toFind.hashCode() % size;

        if (arr[hashCode] != null) {
            for (Object object : arr[hashCode]) {
                if (object.equals(toFind)) {
                    return object;
                }
            }
        }

        return null;
    }

}


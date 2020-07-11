package Lab7;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

class HashTable {
    LinkedList arr[]; // use pure array
    private final static int defaultInitSize = 8;
    private final static double defaultMaxLoadFactor = 0.7;
    private int size;
    private final double maxLoadFactor;
    private int added;
    private double load;

    public HashTable() {
        this(defaultInitSize);
    }

    public HashTable(int size) {
        this(size, defaultMaxLoadFactor);
    }


    public HashTable(int initCapacity, double maxLF) {
        size = initCapacity;
        arr = new LinkedList[size];
        this.maxLoadFactor = maxLF;
    }

    private void calcAlpha() {
        load = (double) added / (double) size;
    }

    private boolean shouldDouble() {
        calcAlpha();
        return load > maxLoadFactor;
    }
    private int mod(int n, int m) {
        return (((n % m) + m) % m);
    }

    public boolean add(Object elem) {
        int pos = (((elem.hashCode() % size) + size) % size);
        Object duplicate = this.get(elem);
        if (duplicate != null && duplicate.equals(elem)) {
            return false;
        } else {
            if (arr[pos] == null) {
                arr[pos] = new LinkedList();
            }
            arr[pos].add(elem);
            added++;
            if (shouldDouble()) doubleArray();
            return true;
        }
    }

    public void simpleAdd(Object elem) {
        int pos = (((elem.hashCode() % size) + size) % size);
        if (arr[pos] == null) {
            arr[pos] = new LinkedList();
        }
        arr[pos].add(elem);
    }


    private void doubleArray() {
        size *= 2;
        LinkedList[] temp = Arrays.copyOf(arr, arr.length);
        arr = new LinkedList[size];
        for (LinkedList linkedList : temp) {
            if (linkedList != null) {
                for (Object o : linkedList) {
                    this.simpleAdd(o);
                }
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            output.append(i).append(": ");
            if (arr[i] != null) {
                for (Object o : arr[i]) {
                    IWithName x = (IWithName) o;
                    output.append(x.getName()).append(", ");
                }
                output.setLength(output.length() - 2);
            }
            output.append("\n");
        }
        return output.toString();
    }

    public Object get(Object toFind) {
        int hash = toFind.hashCode();
        int pos = mod(hash, size);
        if (arr[pos] != null && arr[pos].contains(toFind)) {
            return arr[pos].get(arr[pos].indexOf(toFind));
        }
        return null;
    }
    
    public boolean removeDoc(String ref) {
        Document temp = new Document(ref);
        int hash = temp.hashCode();
        int pos = mod(hash, size);
        if (arr[pos] != null) {
            ListIterator<?> iter = arr[pos].listIterator();
            while (iter.hasNext()) {
                Document temp2 = (Document) iter.next();
                if (temp2.name.equals(ref)) {
                    iter.remove();
                    return true;
                }
            }
        }
        return false;
    }

}	
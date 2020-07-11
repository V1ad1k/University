
import java.util.Iterator;

public class FibonacciIterator implements Iterator<Integer> {
    int a = 0;
    int b = 1;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        int temp = a + b;
        a = b;
        b = temp;
        return temp;
    }
}
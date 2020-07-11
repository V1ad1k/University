
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {
    private T[] array;
    private int pos;
    private int step;

    public ArrayIterator(T[] _array, int _pos, int _step) {
        array = _array;
        pos = _pos;
        step = _step;
    }

    public boolean hasNext() {
        return pos + step < array.length;
    }

    public T next() throws NoSuchElementException {
        if (hasNext()) {
            pos += step;
            return array[pos];
        } else {
            throw new NoSuchElementException();
        }
    }
}

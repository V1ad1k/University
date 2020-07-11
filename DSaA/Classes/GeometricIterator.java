import java.util.Iterator;
import java.util.NoSuchElementException;

public class GeometricIterator implements Iterator<Double> {
    double limit;
    double a;
    double r;

    public GeometricIterator(double limit, double a, double r){
        this.limit = limit;
        this.a = a;
        this.r = r;
    }


    @Override
    public boolean hasNext() {
        return a * r < limit;
    }

    @Override
    public Double next() {
        if (hasNext()){
            a *= r;
            return a;
        }
        else {
            throw new NoSuchElementException();
        }
    }
}

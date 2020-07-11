import java.util.Iterator;

import static java.lang.Math.sqrt;

public class PrimeIterator implements Iterator<Integer> {
    int prime = 2;

    @Override
    public boolean hasNext() {
        return true;
    }

    public int nextPrime(int input) {
        int counter;
        input++;
        while (true) {
            counter = 0;
            for (int i = 2; i <= sqrt(input); i++) {
                if (input % i == 0) counter++;
            }
            if (counter == 0)
                return input;
            else {
                input++;
            }
        }
    }

    @Override
    public Integer next() {
        int temp = prime;
        prime = nextPrime(prime);
        return temp;
    }
}

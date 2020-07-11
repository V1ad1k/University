import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class ProcessFactory {
    public Process getRandomProcess(int id, double lowerBound, double upperBound) {
        //Get random time from CPU
        double time = ThreadLocalRandom.current().nextDouble(lowerBound, upperBound);
        return new Process(id, time);
    }

    public ArrayList<Process> getRandomArray(int size, double lowerBound, double upperBound) {
        Process[] processes = new Process[size];
        for (int i = 0; i < size; i++) {
            processes[i] = getRandomProcess(i, lowerBound, upperBound);
        }
        return new ArrayList<>(Arrays.asList(processes));
    }

    public ArrayList<Process> getFixedArray(int size, FactoryMode mode, double value) throws IncorrectValueException {
        //Sanity checks:
        if (value < 0 && mode != FactoryMode.MATCH) {
            throw new IncorrectValueException();
        }


        Process[] processes = new Process[size];
        for (int i = 0; i < size; i++) {
            switch (mode) {
                case MATCH:
                    processes[i] = new Process(i, i);
                    break;
                case CONSTANT:
                    processes[i] = new Process(i, value);
                    break;
                case LINEAR:
                    processes[i] = new Process(i, value * i);
                    break;
            }
        }
        return new ArrayList<>(Arrays.asList(processes));
    }

    public ArrayList<Process> getFixedArray(int size, FactoryMode mode) throws IncorrectValueException {
        return getFixedArray(size, mode, 0);
    }
}

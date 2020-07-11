package Task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Cluster {
    private final ArrayList<Processor> processors;
    private int tries; // for drawing processors

    public Cluster(int howMany, int threshold) {
        this.processors = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            processors.add(new Processor(i, threshold));
        }
    }

    public Processor selectRandom() {
        Random random = new Random();
        return processors.get(random.nextInt(processors.size()));
    }

    public double getMeanLoad() {
        double mean = 0.0;
        for (Processor processor : processors) {
            mean += processor.getCurrentLoad();
        }
        mean /= processors.size();
        return mean;
    }

    public Processor drawLowerThan(int threshold) {
        ArrayList<Processor> candidates = new ArrayList<>();
        for (Processor processor : processors) {
            if (processor.getCurrentLoad() < threshold) {
                candidates.add(processor);
            }
        }
        if (candidates.size() == 0) {
            return drawLowerThan(threshold + 1);
        }
        if (candidates.size() == 1) {
            tries++;
            return candidates.get(0);
        }
        Random random = new Random();
        tries++;
        return candidates.get(random.nextInt(candidates.size()));
    }

    public int getTries() {
        return tries;
    }

    public void resetTries() {
        tries = 0;
    }

    public void updateTicks() {
        for (Processor processor : processors) {
            processor.updateTick();
        }
    }

    public int updateHeld() {
        int total = 0;
        for (Processor processor : processors) {
            total += processor.removeFinished();
        }
        return total;
    }

    public double getMedianLoad() {
        ArrayList<Integer> loads = new ArrayList<>();
        for (Processor processor : processors) {
            loads.add(processor.getCurrentLoad());
        }
        Collections.sort(loads);
        if (loads.size() % 2 == 1) {
            return (double) loads.get(loads.size() / 2);
        } else {
            return (double) (loads.get((loads.size() / 2) - 1) + loads.get(loads.size() / 2)) / 2.0;
        }
    }

    public boolean isFinished() {
        for (Processor processor : processors) {
            if (processor.getCurrentLoad() > 0) {
                return false;
            }
        }
        return true;
    }
}

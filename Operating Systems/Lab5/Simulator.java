package Task5;

import java.util.ArrayList;
import java.util.HashMap;

class Simulator {
    private final int r;
    private final int z;
    private final int N;
    private final int p;
    private final ArrayList<Process> processes;
    private final HashMap<Algorithms, ArrayList<Double>> meanLoad = new HashMap<>();
    private final HashMap<Algorithms, ArrayList<Double>> medianLoad = new HashMap<>();
    private final HashMap<Algorithms, ArrayList<Integer>> loadRequests = new HashMap<>();
    private final HashMap<Algorithms, ArrayList<Integer>> migrated = new HashMap<>();
    private Cluster cluster;
    private int currentTick = 0;
    private int requests = 0;
    private int migrations = 0;

    public Simulator(int p, int r, int z, int N, ArrayList<Process> processes) {
        this.cluster = new Cluster(N, p);
        this.r = r;
        this.z = z;
        this.N = N;
        this.p = p;
        this.processes = processes;
        for (Algorithms algorithm : Algorithms.values()) {
            meanLoad.put(algorithm, new ArrayList<>());
            medianLoad.put(algorithm, new ArrayList<>());
            loadRequests.put(algorithm, new ArrayList<>());
            migrated.put(algorithm, new ArrayList<>());
        }
    }

    private void reset() {
        cluster = new Cluster(N, p);
        requests = 0;
        migrations = 0;
        currentTick = 0;
    }

    private void updateMeanLoadStat(Algorithms algorithm) {
        double temp = cluster.getMeanLoad();
        try {
            meanLoad.get(algorithm).set(currentTick, temp);
        } catch (IndexOutOfBoundsException e) {
            meanLoad.get(algorithm).add(temp);
        }
    }

    private void updateMedianLoadStat(Algorithms algorithm) {
        double temp = cluster.getMedianLoad();
        try {
            medianLoad.get(algorithm).set(currentTick, temp);
        } catch (IndexOutOfBoundsException e) {
            medianLoad.get(algorithm).add(temp);
        }
    }

    private void updateLoadRequestsStat(Algorithms algorithm) {
        try {
            loadRequests.get(algorithm).set(currentTick, requests);
        } catch (IndexOutOfBoundsException e) {
            loadRequests.get(algorithm).add(requests);
        }
    }

    private void updateMigrationsStat(Algorithms algorithm) {
        try {
            migrated.get(algorithm).set(currentTick, migrations);
        } catch (IndexOutOfBoundsException e) {
            migrated.get(algorithm).add(migrations);
        }
    }

    private void updateStats(Algorithms algorithm) {
        updateLoadRequestsStat(algorithm);
        updateMeanLoadStat(algorithm);
        updateMedianLoadStat(algorithm);
        updateMigrationsStat(algorithm);
    }

    private void randAlgorithm() {
        int handled = 0;
        while (handled != processes.size()) {
            cluster.updateHeld();
            Process process = processes.get(handled);
            if (process.getStartsAtTick() > currentTick) {
                currentTick++;
                cluster.updateTicks();
                updateStats(Algorithms.RAND);
                continue;
            }
            Processor marked = cluster.selectRandom();
            marked.insert(process);
            requests++;
            if (marked.getCurrentLoad() + process.getLoad() > marked.getThreshold()) {
                for (int i = 0; i < z; i++) {
                    Processor candidate = cluster.selectRandom();
                    requests++;
                    if (candidate.getCurrentLoad() + process.getLoad() < candidate.getThreshold()) {
                        marked.transferLast(candidate);
                        migrations++;
                        break;
                    }
                }
            }
            handled++;
            updateStats(Algorithms.RAND);
        }
        finish(Algorithms.RAND);
    }

    private int updateHandled(int handled, Process process, Processor processor) {
        processor.insert(process);
        requests++;
        if (processor.getCurrentLoad() + process.getLoad() > processor.getThreshold()) {
            Processor random = cluster.drawLowerThan(p);
            requests += cluster.getTries();
            cluster.resetTries();
            migrations++;
            processor.transferLast(random);
        }
        handled++;
        return handled;
    }

    private void findAlgorithm() {
        int handled = 0;
        while (handled != processes.size()) {
            cluster.updateHeld();
            updateStats(Algorithms.FIND);
            Process process = processes.get(handled);
            if (process.getStartsAtTick() > currentTick) {
                currentTick++;
                cluster.updateTicks();
                continue;
            }
            Processor processor = cluster.selectRandom();
            handled = updateHandled(handled, process, processor);
        }
        finish(Algorithms.FIND);
    }

    private void seizeAlgorithm() {
        int handled = 0;
        while (handled != processes.size()) {
            Processor processor = cluster.selectRandom();
            int removed = cluster.updateHeld();
            if (removed > 0) {
                int fails = 0;
                while (processor.getCurrentLoad() < r && fails < z) {
                    Processor randomProcessor = cluster.selectRandom();
                    if (randomProcessor.getId() != processor.getId() && randomProcessor.getCurrentLoad() >= p) {
                        randomProcessor.transferFirst(processor);
                        fails = 0;
                        requests++;
                        migrations++;
                    } else {
                        fails++;
                    }
                }
            }
            updateStats(Algorithms.SEIZE);
            Process process = processes.get(handled);
            if (process.getStartsAtTick() > currentTick) {
                currentTick++;
                cluster.updateTicks();
                continue;
            }
            handled = updateHandled(handled, process, processor);
        }
        finish(Algorithms.SEIZE);
    }

    private void finish(Algorithms algorithm) {
        while (!cluster.isFinished()) {
            updateStats(algorithm);
            cluster.updateTicks();
            cluster.updateHeld();
            currentTick++;
        }
    }

    public void simulate() {
        randAlgorithm();
        reset();
        findAlgorithm();
        reset();
        seizeAlgorithm();
        reset();
    }

    public HashMap<Algorithms, ArrayList<Double>> getMedianLoad() {
        return medianLoad;
    }

    public HashMap<Algorithms, ArrayList<Double>> getMeanLoad() {
        return meanLoad;
    }

    public HashMap<Algorithms, ArrayList<Integer>> getLoadRequests() {
        return loadRequests;
    }

    public HashMap<Algorithms, ArrayList<Integer>> getMigrated() {
        return migrated;
    }
}

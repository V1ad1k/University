package Task5;

class Process implements Comparable {
    private final int load;
    private final int startsAtTick;
    private final int duration;

    public Process(int load, int startsAtTick, int duration) {
        this.load = load;
        this.startsAtTick = startsAtTick;
        this.duration = duration;
    }

    public int getStartsAtTick() {
        return startsAtTick;
    }

    public int getLoad() {
        return load;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public int compareTo(Object other) {
        Process o = (Process) other;
        return this.getStartsAtTick() - o.getStartsAtTick();
    }
}

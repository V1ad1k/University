package Task4;

import java.util.ArrayList;

public class Rand extends Fifo {
    public Rand(int ramSize, ArrayList<Page> q){
        super(ramSize, q);
    }
    @Override
    public int callPage(Page called){
        ///if page fault
        int rem = (int) (Math.random() * (ram.size()-1));
        if (!ram.contains(called)) {
            ram.set(rem, called);
            faults++;
            return 1;
        }
        return 0;
    }
}

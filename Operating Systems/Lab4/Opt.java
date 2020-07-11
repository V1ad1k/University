package Task4;

import java.util.ArrayList;

public class Opt extends Fifo {
    public Opt(int ramSize, ArrayList<Page> q){
        super(ramSize, q);
    }
    @Override
    public int callPage(Page called){
        ///if page fault
        if (!ram.contains(called)) {
            ram.set(toReplace(), called);
            faults++;
            return 1;
        }
        return 0;
    }
    public int toReplace(){
        int processIndex = 0;
        int ramIndex = -1;

        ///if no index in the ram is found in queue we select random
        ////nonon what we need is we need to remove one that is never going to be used
        //if possible
        int outIndex = (int) (Math.random() * (ram.size()-1));
        for(Page p: ram){
            ramIndex++;
            int index = queue.indexOf(p);
            if(index < 0){
                // if p is not at all in the queue we remove it
                return ramIndex;
            }
            if(index >= processIndex){
                processIndex = index;
                outIndex = ramIndex;
            }
        }
        return outIndex;
    }
}

////todo we might want to cange it so that it works in such a way that
///instead of looking at the fartherst page call, we should look
///for when will a call be needed, and then select the one with
///the longest need time.
//Never mind it works find since this returns the closes index
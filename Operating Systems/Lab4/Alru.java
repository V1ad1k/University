package Task4;

import java.util.ArrayList;

public class Alru extends Fifo {
    public Alru(int ramSize, ArrayList<Page> q){
        super(ramSize, q);
    }
    @Override
    public int callPage(Page called){
        //when page called we add it to the beginning of the list

        ///but first we check whether there wes a page fault
        ///if page fault
        if(toRemove >= ram.size()){
            toRemove = 0;
        }
        if (!ram.contains(called)) {
            Page toRem = ram.get(toRemove);
            while(toRem.bit){
                toRem.bit = false;
                toRemove++;
                if(toRemove >= ram.size()){
                    toRemove = 0;
                }
                toRem = ram.get(toRemove);
            }
            ram.set(toRemove, called);
            toRemove++;
            faults++;
            return 1;
        }
        else {
            ram.get(ram.indexOf(called)).bit = true;
            return 0;
        }
    }
}

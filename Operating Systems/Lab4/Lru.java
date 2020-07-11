package Task4;

import java.util.ArrayList;

/*
LRO will work in such a way that what ever is most recently used goes to the
front of the linked list and every thing else is pushed down
 */
public class Lru extends Fifo{
    public Lru(int ramSize, ArrayList<Page> q){
        super(ramSize, q);
    }
    //for frame allocation
    public Lru(ArrayList<Page> q, ArrayList<Page> _ram){
        super(q, _ram);
    }
    @Override
    public int callPage(Page called){
        //when page called we add it to the beginning of the list

        ///but first we check whether there wes a page fault
        ///if page fault
        if (!ram.contains(called)) {
            ram.add(0, called);
            ram.remove(ram.size()-1);
            faults++;
            return 1;
        }
        else {
            ram.remove(called);
            ram.add(0, called);
            return 0;
        }
    }
}

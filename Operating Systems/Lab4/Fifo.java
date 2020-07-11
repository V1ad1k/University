package Task4;

import java.util.ArrayList;
import java.util.ListIterator;

public class Fifo {
    public ArrayList<Page> queue;
    public ArrayList<Page> ram;
    int proPosition;
    int toRemove;
    int faults;

    public Fifo(int ramSize, ArrayList<Page> q){
        ram = loadMemory(ramSize);
        queue = q;
        proPosition = 0;
        toRemove = 0;
        faults = 0;
    }
    public Fifo(ArrayList<Page> q, ArrayList<Page> _ram){
        queue = q;
        ram = _ram;
        proPosition = 0;
        toRemove = 0;
        faults = 0;
    }
    public static ArrayList<Page> loadMemory(int size){
        ///in FIFO we simply load the memory with page locations
        ArrayList<Page> _ram = new ArrayList<Page>();
        for (int i=0; i < size; i++){
            _ram.add(new Page(i));
        }
        return _ram;
    }

    ///
    public void mainClock(){
        ListIterator<Page> it = queue.listIterator();
        while(it.hasNext()){
            callPage(it.next());
        }
    }
    public int callPage(Page called){
        ///if page fault
        if(toRemove >= ram.size()){
            toRemove = 0;
        }
        if (!ram.contains(called)) {
            ram.set(toRemove, called);
            toRemove++;
            faults++;
            return 1;
        }
        return 0;
    }
}

/*
Say we create a new array list with 128 pages 0-127
Say all possible pages are from 0-255

if we call a pae > 127 therer is a page faouf

////
how do we find in ram the process that will not be called the longest?

 */
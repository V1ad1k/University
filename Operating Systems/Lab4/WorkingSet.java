package Task4;

import java.util.ArrayList;
import java.util.ListIterator;

public class WorkingSet extends FrameAllocation {
    public static final int interval = 16;
    public WorkingSet(ArrayList<Process> _processes) {
        super(_processes);
    }
    @Override
    public void run(){
        allocateFrames();
        int timeRunning = 0;
        while(processes.size() > 0){
            executeAll();
            timeRunning++;
            if(timeRunning % interval == 0 && timeRunning != 0) createWorkingSet();
        }
    }
    public void createWorkingSet(){
        //what we do is we take the amount of pages equal to the interval back from
        //the current position index and transfer it to the working set;
        ListIterator<Process> it = processes.listIterator();
        while(it.hasNext()){
            Process p = it.next();
            for (int i = interval; i > 0; i--){
                ArrayList<Page> newSet = new ArrayList<>();
                newSet.add(p.pageSet.get(p.currentPage - i));
                p.frameSet = newSet;

            }

        }
    }
}


/*
we need to create the so called working set.
let's say it
*/
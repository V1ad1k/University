package Task4;

import java.util.ArrayList;
import java.util.ListIterator;

public class PageFaultFreq extends FrameAllocation{
    public static final int upperBound = 4;
    public static final int lowerBound = 2;
    public static final int interval = 4;
    public PageFaultFreq(ArrayList<Process> _processes) {
        super(_processes);
    }

    @Override @SuppressWarnings("Duplicates")
    public void executeAll() {
        //todo execute all the processes and get page faults
        //  Lru lru = new Lru(processes.get(0).pageSet, processes.get(0).frameSet);
        ///after short time each process should roughly have the pages it needs;
        ///todo some loop that executes all the processes one step at the time one  by one
        ListIterator<Process> it = processes.listIterator();
        while(it.hasNext()){
            Process pro = it.next();
            Lru lru = new Lru(pro.pageSet, pro.frameSet);
            int fault = lru.callPage(pro.pageSet.get(pro.currentPage));
            totalFaults += fault;
            pro.pageFaults += fault;
            ///todo we should implement finding the frame in other processes
            pro.currentPage++;
            if(pro.currentPage >= pro.pageSet.size()){
                //this process is completed so remove
                //todo but we also need to redistribute the frames
                it.remove();
            }
            if (executionTime % interval == 0){
                //if the interval went by
                if(pro.pageFaults >= upperBound){
                    int index = (int) Math.random() * (pro.frameSet.size()-1);
                    pro.frameSet.add(new Page(pro.frameSet.get(index).id));
                }
                else if(pro.pageFaults < lowerBound){
                    int index = (int) Math.random() * (pro.frameSet.size()-1);
                  pro.frameSet.remove(new Page(pro.frameSet.get(index).id));
                }
            }
        }
        // return -1;
    }
}

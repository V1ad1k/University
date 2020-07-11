package Task4;
import java.util.ArrayList;
import java.util.ListIterator;
///todo what we haven't done yet is

///this is the default frame allocator which uses the proportional algorithm;
public class FrameAllocation {
    public int totalFaults;
    public int executionTime;
    public FrameAllocation(ArrayList<Process> _processes){
        totalFaults = 0;
        processes = _processes;
        frames = Fifo.loadMemory(Main.ramSize);
        executionTime = 0;
    }
    //todo the algorithm has to decide the process's "ram" size
    public ArrayList<Process> processes;
    public ArrayList<Page> frames;
    //public Lru pageSelector;
    public void allocateFrames(){
        ///lets assume the simplest case that we simply allocate frames one by one
        int S = getRequiredSize(); ///
        ///frames to allocate
        /////the minimum required memory should be enough for each process I suppose
        int index = 0;
        ListIterator<Process> it = processes.listIterator();
        while(it.hasNext()){
            Process p = it.next();
            double allocation = (double) p.minMemory * frames.size() / S;
            //todo make sure there is no problems with precision here
            for (int j = 0; j < allocation; j++){
                ///todo for now we are just in line starting from 0;
                p.frameSet.add(frames.remove(0));
            }

        }
    }
    protected int getRequiredSize(){
        int ret = 0;
        for (Process p: processes){
            ret += p.minMemory;
        }
        return ret;
    }
    public void run(){
        allocateFrames();
        while(processes.size() > 0){
            executionTime++;
            executeAll();
        }
    }
    @SuppressWarnings("Duplicates")
    public void executeAll(){
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
        }
       // return -1;
    }
}

/*
Let's say we use the proportional allocation of frames
I see it like this, we simply run allocation for each process
and we use local algorithms for each process.
 */
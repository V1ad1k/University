package Task4;

import java.util.ArrayList;
import java.util.ListIterator;

public class RandomAllocation extends FrameAllocation{
    public RandomAllocation(ArrayList<Process> _processes) {
        super(_processes);
    }
    @Override
    public void allocateFrames(){
        ///lets assume the simplest case that we simply allocate frames one by one
        int S = getRequiredSize(); ///
        ///frames to allocate
        /////the minimum required memory should be enough for each process I suppose
        int index = 0;
        ListIterator<Process> it = processes.listIterator();
        while(it.hasNext()){
            Process p = it.next();
            double allocation = Math.random() * frames.size() - 2;
            //todo make sure there is no problems with precision here
            for (int j = 0; j < allocation; j++){
                ///todo for now we are just in line starting from 0;
                p.frameSet.add(frames.remove(0));
            }

        }
    }
}

package Task4;

import java.util.ArrayList;
import java.util.Arrays;

public class Main2 {
    public static final int maxLocality = 12;
    public static final int nOfFrames = 128;
    public static final int maxSize = 32; //max size is multplied by loclality

    public static void main(String[] args) {
        ArrayList<Process> list = ProGenerator.simpleProcessSet();
       // ArrayList<Process> list2 = copyList(list);
/*
        FrameAllocation proportional = new FrameAllocation(list);
        RandomAllocation random = new RandomAllocation(ProGenerator.simpleProcessSet2());
        WorkingSet workSet = new WorkingSet(ProGenerator.simpleProcessSet3());
        PageFaultFreq freq = new PageFaultFreq(ProGenerator.simpleProcessSet4());

        System.out.println("Proportional");
        runSim(proportional);
        System.out.println("Random");
        runSim(random);
        ///the reason work set has more page faults is because it prevents trashing
        System.out.println("Work Set");
        runSim(workSet);

        System.out.println("Page fault frequency");
        runSim(freq);
*/

        /////////////////*///////////////////////
        ArrayList<Process> testList = ProGenerator.proList(16);
        ArrayList<Process> testList2 = ProGenerator.copyList(testList);
        ArrayList<Process> testList3 = ProGenerator.copyList(testList);
        ArrayList<Process> testList4 = ProGenerator.copyList(testList);

        FrameAllocation proportional = new FrameAllocation(testList);
        RandomAllocation random = new RandomAllocation(testList2);
        WorkingSet workSet = new WorkingSet(testList3);
        PageFaultFreq freq = new PageFaultFreq(testList4);

        System.out.println("Proportional");
        runSim(proportional);
        System.out.println("Random");
        runSim(random);
        ///the reason work set has more page faults is because it prevents trashing
        System.out.println("Work Set");
        runSim(workSet);

        System.out.println("Page fault frequency");
        runSim(freq);




    }
    public static void runSim(FrameAllocation frames){
        frames.run();
        System.out.println("Page faults: " + frames.totalFaults + '\n');
    }
    public static ArrayList<Process> copyList(ArrayList<Process> list){
        ArrayList<Process> out = new ArrayList<>();
        for(Process p: list){
            out.add(p);
        }
        return out;
    }
}

class ProGenerator{
    public static ArrayList<Process> copyList(ArrayList<Process> inList){
        ArrayList<Process> out = new ArrayList<>();
        for (Process p: inList){
            out.add(new Process(p.pageSet));
        }
        return out;
    }
    public static ArrayList<Process> proList(int size){
        ArrayList<Process> list= new ArrayList<>();
        for (int i= 0; i< size; i++){
            list.add(randomPro());
        }
        return list;
    }
    public static Process randomPro(){
        //todo
        ///first let's create a range of how many processes can a process have
        ArrayList<Page> pages = new ArrayList<>();
        int size = (int) (Math.random() * Main2.maxSize)+1;
        for (int i = 0; i < size; i++){
            pages.addAll(localityPageSet());
        }
        return new Process(pages);
    }
    public static ArrayList<Page> localityPageSet(){
        ArrayList<Page> pages = new ArrayList<>();
        int range = (int) (Math.random() * Main2.maxLocality) + 1;
        for (int i = 0; i < range; i++){
            pages.add(new Page((int) (Math.random() * Main.memorySize)));
        }
        return pages;
    }
    public static ArrayList<Page> simplePageSet(){
        ArrayList<Page> pages = new ArrayList<Page>();
        Page[] toAdd = {new Page(1), new Page(2),
                new Page(1),new Page(3), new Page(3)
        , new Page(4)};
        pages.addAll(Arrays.asList(toAdd));
        return pages;
    }
    public static ArrayList<Process> simpleProcessSet(){
        ArrayList<Process> out = new ArrayList<Process>();
        out.add(new Process(simplePageSet()));
        out.add(new Process(simplePageSet()));
        return out;
    }
    public static ArrayList<Process> simpleProcessSet2(){
        ArrayList<Process> out = new ArrayList<Process>();
        out.add(new Process(simplePageSet()));
        out.add(new Process(simplePageSet()));
        return out;
    }
    public static ArrayList<Process> simpleProcessSet3(){
        ArrayList<Process> out = new ArrayList<Process>();
        out.add(new Process(simplePageSet()));
        out.add(new Process(simplePageSet()));
        return out;
    }
    public static ArrayList<Process> simpleProcessSet4(){
        ArrayList<Process> out = new ArrayList<Process>();
        out.add(new Process(simplePageSet()));
        out.add(new Process(simplePageSet()));
        return out;
    }
}

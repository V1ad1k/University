package Task4;

import java.util.ArrayList;

public class Main {
    public static final int memorySize = 256;
    public static final int ramSize = 124;

    public static void runSimulation(Fifo fifo){
        fifo.mainClock();
        System.out.println("total calls: " + fifo.queue.size());
        System.out.println("page faults: " + fifo.faults + '\n');
    }

    public static void main(String[] args) {
        ArrayList<Page> testList = Generator.proList1(1024, 16, 4);

        Fifo fifo = new Fifo(ramSize, testList);
        System.out.println("FIFO:");
        runSimulation(fifo);

        Fifo Rand = new Rand(ramSize, testList);
        System.out.println("Rand:");
        runSimulation(Rand);

        Fifo Opt = new Opt(ramSize, testList);
        System.out.println("Opt:");
        runSimulation(Opt);

        Fifo Lru = new Lru(ramSize, testList);
        System.out.println("Lru:");
        runSimulation(Lru);

        Fifo Alru = new Alru(ramSize, testList);
        System.out.println("ALRU:");
        runSimulation(Alru);
    }
}




class Generator{
    public static ArrayList<Page> proList1(int np, int similarity,
                                           int locality){
        ////effectively similarity factor is the number of processes that are going to repeat themselves
        /// locality factor is the size of the process
        ArrayList<Page> out = new ArrayList<Page>();
        ArrayList<Page> similarSet = generateSet(similarity, locality);

        //First generate a perfectly symmetrical and local set
        for (int i = 0; i < np/similarity; i++){
            out.addAll(similarSet);
        }
        randomizeSet(out, similarity, locality);
        return out;
    }
    public static void randomizeSet(ArrayList<Page> in, int similarity, int locality){
        //todo fist we rendomize the set, we take 1/10 of the sets and change them
        for (int i = 0 ,count = 0, size = in.size()/similarity; i < size; i++){

            //generate a start index from 1 to size() - locality
            ArrayList<Page> newSet = generateSet(similarity, locality);

            for(int j = 0; j < similarity; j++){
                count++;
                //1/10 sets different
                if(i % 10 == 0) {
                    int startIndex = (int)(Math.random() * (in.size()-similarity) + 1);
                    in.set(startIndex++, newSet.get(j));
                }
                ///1/100 processes with jump
                if(count % 100 == 0){
                    int index = (int)(Math.random() * in.size() + 1);
                    int page = (int)(Math.random() * Main.memorySize + 1);
                    in.set(index, new Page(page));
                }
            }
        }
    }
    public static ArrayList<Page> generateProcess(int calls, boolean jump){
        ///generates a random page index from 0 to 256
        int pageLocation = (int)(Math.random() * Main.memorySize + 1);
        ArrayList<Page> out = new ArrayList<Page>();
        for (int i = 0; i < calls; i++){
            out.add(new Page(pageLocation));
        }
        if (jump){
            ///if jump == true we make a random call a different location
            out.set((int)(Math.random() * calls + 1), new Page((int)(Math.random() * Main.memorySize + 1)));
        }
        return out;
    }
    public static ArrayList<Page> generateSet(int a, int pSize){
        ArrayList<Page> out = new ArrayList<Page>();
        for (int i = 0; i < a; i++){
            out.addAll(generateProcess(pSize, false));
        }
        return out;
    }
}

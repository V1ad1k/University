package Task4;

import java.util.ArrayList;

public class Process {
    int runningTime;
    ArrayList<Page> pageSet;
    ArrayList<Page> frameSet;
    ArrayList<Page> workingSet;
    int currentPage;
    int minMemory;
    public int pageFaults;
    public Process(ArrayList<Page> pages, ArrayList<Page> frames){
        pageSet = pages;
        frameSet = frames;
        runningTime = pages.size();
        currentPage = 0;
        minMemory = getUniquePages();
    }
    public Process(ArrayList<Page> pages, int time){
        pageSet = pages;
        runningTime = time;
        currentPage = 0;
        minMemory = getUniquePages();
    }
    public Process(ArrayList<Page> pages){
        pageSet = pages;
        runningTime = pages.size();
        currentPage = 0;
        frameSet = new ArrayList<Page>();
        minMemory = getUniquePages();
        workingSet = new ArrayList<Page>();
    }
    private int getUniquePages(){
        //todo an improved method can be used
        int start = -1;
        int count = 0;
        for (Page p: pageSet){
            if(p.id != start){
                count++;
                start = p.id;
            }
        }
        return count;
    }
}

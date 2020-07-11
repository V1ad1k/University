package Lab11;

public class DisjointSetForest implements DisjointSetDataStructure {

    private class Element{
        int rank;
        int parent;
        private Element(int rank, int parent){
            this.rank = rank;
            this.parent = parent;
        }
    }

    Element []arr;

    public DisjointSetForest(int size) {
        arr = new Element[size];
    }

    @Override
    public void makeSet(int item) {
        //todo check for exceptinos
        if(item >= arr.length) return;
        arr[item] = new Element(0, item);
    }
    @Override
    public int findSet(int item) {
        if (item >= arr.length || item < 0) return -1;
        //?private find path?
        //use parh compression to simply the path by pointing each node to the parent
        if (item != arr[item].parent){
            arr[item].parent = findSet(arr[item].parent);
        }
        return arr[item].parent;
    }

    @Override
    public boolean union(int itemA, int itemB) {
        //point one root into another root
        if (itemA >= arr.length || itemA < 0 || itemB >= arr.length || itemB < 0) return false;
        if(findSet(itemA) == findSet(itemB)) return false;
        //use the one with less nodes points to one with more nodes
        //maintain a rank (upper bound of the height of the node)
        //use root with smaller rank to point to root with larger rank
        link(findSet(itemA), findSet(itemB));
        return true;
    }

    @Override
    public int countSets() {
        return 0;
    }

    private void link(int itemA, int itemB){
        if (arr[itemA].rank > arr[itemB].rank){
            arr[itemB].parent = itemA;
        } else{
            arr[itemA].parent = itemB;
            if (arr[itemA].rank == arr[itemB].rank){
                arr[itemB].rank++;
            }
        }
    }


    @Override
    public String toString() {
        String ret = "Disjoint sets as forest:";
        for(int i = 0, size = arr.length; i < size; i++){
            ret += "\n" + i + " -> " + arr[i].parent;
        }
        return ret;
    }
}

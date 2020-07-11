package Lab11;


import java.util.*;
import java.util.Map.Entry;

public class Graph {
    int arr[][];
    //TODO? Collection to map Document to index of vertex
    // You can change it

    HashMap<String,Integer> nameToInt = new HashMap<>();
    HashMap<Integer, String> int2Name = new HashMap<>();
    @SuppressWarnings("unchecked")
    //TODO? Collection to map index of vertex to Document

    private int colors[];
    public String string = "";
    Document[] docs;
    public static int NULL = 0;


    // The argument type depend on a selected collection in the Main class
    public Graph(SortedMap<String,Document> internet){
        int size=internet.size()+1;
        arr=new int[size][size];
        for(int[] row:arr){
            Arrays.fill(row, 0);
        }
        //mapping doc names as keys and integers
        nameToInt = new HashMap<String, Integer>(size);
        docs = new Document[size];
        int integer = 1;
        for (Document doc: internet.values()){
            nameToInt.put(doc.getName(), integer);
            docs[integer] = doc;
            int2Name.put(integer, doc.getName());
            integer++;
        }
        for (int i = 1; i < size; i++){
            for(Link link: docs[i].link.values()){
                arr[i][nameToInt.get(link.ref)] = link.weight;
            }
        }
    }

    public String bfs(String start) {
        //todo
        Queue<Integer> q =new PriorityQueue<>();
        colors = new int[arr.length];
        Integer from = nameToInt.get(start);
        if (from == null) return null;
        string = docs[from].getName() + ", ";
        colors[from] = 1;
        enQueue(q, arr[from], from);
        while (!q.isEmpty()){
            int vertex = q.remove();
            enQueue(q, arr[vertex], vertex);
        }
        return string.substring(0, string.length()-2);
    }
    private void enQueue(Collection<Integer> q, int[] vertex, int v){
        int index = 0;
        for(int i: vertex){
            if (i != NULL && colors[index] == 0){
                colors[index] = 1;
                q.add(index);
                string += docs[index].getName() + ", ";
            }
            index++;
        }
    }
    public String dfs(String start) {
        //todo use a string buffer
        Integer from = nameToInt.get(start);
        colors = new int[arr.length];
        if (from == null) return null;
        string = "";//docs[from].getName() + ", ";
        colors[from] = 1;
        dfsVisit(arr[from], from);
        return string.substring(0, string.length()-2);
    }
    private void dfsVisit(int[] vertex, int v){
        int index = 0;
        colors[v] = 1;
        string += docs[v].getName() + ", ";
        for(int i: vertex){
            if (i != NULL && colors[index] == 0) {
                dfsVisit(arr[index], index);
            }
            index++;
        }
    }

    ArrayList<String> ret = new ArrayList<>();
    int counter = 0;
    public ArrayList<String> getCC(String s){
        counter = 0;
        ret = new ArrayList<>();
        return getComponents(s);
    }
    public ArrayList<String> getComponents(String s){
        if(!ret.contains(s)){
            ret.add(s);
        }
        for(int i=1; i<arr[nameToInt.get(s)].length; i++){
            if(arr[nameToInt.get(s)][i]!=0 && !ret.contains(docs[i].name)){
                ret.add(docs[i].name);
            }
        }
        if(counter < ret.size()-1){
            counter++;
            return getComponents(ret.get(counter));
        }
        return ret;
    }


    public int connectedComponents() {
        int cc = 0;
        ArrayList<String> sets = new ArrayList<>();
        for(String s: nameToInt.keySet()){
            if(!sets.contains(s)){
                cc++;
                ArrayList<String> toAdd = getCC(s);
                for(String s1: toAdd){
                    sets.add(s1);
                }
            }
        }
        return cc;
    }


    int[] distances;
    String[] predecessors;

    public String DijkstraSSSP(String startVertexStr) {
        if(!nameToInt.containsKey(startVertexStr)){
            return null;
        }
        int currDistance = 0;
        ArrayList<String> vertices = getComponents(startVertexStr);
        //System.out.println(vertices);
        String result="";
        int currIndex = nameToInt.get(startVertexStr);
        distances = new int[arr.length];
        predecessors = new String[arr.length];
        PriorityQueue<String> queue = new PriorityQueue<>();
        for(String v:vertices){
            distances[nameToInt.get(v)]=Integer.MAX_VALUE;
            queue.add(v);
        }
        distances[currIndex]=0;
        int co=1;
        ArrayList<String> visited = new ArrayList<>();
        ArrayList<String> path = new ArrayList<>();
        while(!queue.isEmpty()){
            //System.out.println("loop "+ co);
            co++;
            path.add(int2Name.get(currIndex));
            /*System.out.println("Current node: "+int2Name.get(currIndex));
            System.out.println("Current distance: " + currDistance);
            System.out.println("Current queue state: " + queue);
            System.out.println("Current path: "+ path);*/
            String u = removeMin(queue, int2Name.get(currIndex));
            if(u==null){
                break;
            }
            //System.out.println("u value: "+u);
            visited.add(u);
            queue.remove(u);
            ArrayList<String> neighbours = new ArrayList<>();
            for(int i=1; i<arr[nameToInt.get(u)].length; i++){
                if(arr[nameToInt.get(u)][i]!=0){
                    neighbours.add(int2Name.get(i));
                }
            }
            for(String s:neighbours){
                if(distances[nameToInt.get(s)]>currDistance + arr[nameToInt.get(u)][nameToInt.get(s)]) {
                    distances[nameToInt.get(s)] = currDistance + arr[nameToInt.get(u)][nameToInt.get(s)];
                    predecessors[nameToInt.get(s)]=u;
                    //System.out.println("Distance to "+s+" "+distances[nameToInt.get(s)]);
                }
            }
            int minD = Integer.MAX_VALUE;
            for(String s:neighbours){
                if(distances[nameToInt.get(s)]<minD && !visited.contains(s)){
                    minD = distances[nameToInt.get(s)];
                    currIndex = nameToInt.get(s);
                }
            }
            currDistance = distances[currIndex];

        }
        predecessors[nameToInt.get(startVertexStr)] = startVertexStr;
        for(int i=1; i<predecessors.length; i++){
        }
        for(int i=1; i<predecessors.length; i++){
            if(predecessors[i]==null){
                result+="no path to "+int2Name.get(i)+"\n";
                continue;
            }
            if(predecessors[i].equals(int2Name.get(i))){
                result+= int2Name.get(i)+"="+distances[i]+"\n";
                continue;
            }
            //k val to get to the last predecessor
            int k = i;
            boolean noChange = false;
            String revOrder="";
            revOrder=int2Name.get(i);
            while(!noChange){
                revOrder+=predecessors[k];
                String temp = predecessors[k];
                k = nameToInt.get(predecessors[k]);
                if(temp.equals(predecessors[k])){
                    noChange=true;
                }
            }
            char[] rev = revOrder.toCharArray();
            for(int j=rev.length-1; j>=0; j--){
                if(j!=0){
                    result += rev[j]+"->";
                }else{
                    result += rev[j]+"=";
                }
            }
            result+= distances[i]+"\n";
        }
        return result;
    }

    public String removeMin(PriorityQueue<String> queue, String currentV){
        int min = Integer.MAX_VALUE;
        String toRem = null;
        for(String s:queue){
            if(s.equals(currentV)){
                toRem=s;
                break;
            }
            if(arr[nameToInt.get(currentV)][nameToInt.get(s)]!=0 && arr[nameToInt.get(currentV)][nameToInt.get(s)]<min){
                toRem = s;
                min = arr[nameToInt.get(s)][nameToInt.get(currentV)];
            }
        }
        return toRem;
    }


}
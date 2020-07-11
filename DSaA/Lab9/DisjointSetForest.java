package Lab9;



public class DisjointSetForest implements DisjointSetDataStructure {
	
	private class Element{
		int rank;
		int parent;
		public Element(int num) {
			parent=num;
			rank=0;
		}
	}

	Element []arr;
	
	public DisjointSetForest(int size) {
		arr=new Element[size];
		for(int i=0;i<size;i++) {
			makeSet(i);
		}
	}
	
	@Override
	public void makeSet(int item) {
		Element elem=new Element(item);
		arr[item]=elem;
	}

	@Override
	public int findSet(int item) {
		if(item<arr.length||item>-1) {
			if(arr[item].parent!=item) {
				arr[item].parent=findSet(arr[item].parent);
			}
			return arr[item].parent;
			}else {
				return -1;
			}
	}

	@Override
	public boolean union(int itemA, int itemB) {
		if(itemA>arr.length||itemA<0||itemB>arr.length||itemB<0) return false;
		itemA=findLastParent(itemA);
		itemB=findLastParent(itemB);
		if(arr[itemA].parent!=arr[itemB].parent){
			if(arr[itemA].rank>arr[itemB].rank) {
				arr[itemB].parent=itemA;
			}else if(arr[itemA].rank>arr[itemB].rank) {
				arr[itemA].parent=itemB;
			}else if(arr[itemA].rank==arr[itemB].rank) {
				arr[itemA].parent=itemB;
				arr[itemA].rank++;
			}
			return true;
		}else {
			return false;
		}
	}
	public int findLastParent(int item) {
		while(item!=arr[item].parent) {
			item=arr[item].parent;
		}
		return item;
	}
	
	@Override
	public String toString() {
		String ret="Disjoint sets as forest:\n";
		for(int i=0;i<arr.length;i++) {
			ret=ret+i+" -> "+arr[i].parent+"\n";
		}
		return ret.substring(0, ret.length()-1);
	}
}
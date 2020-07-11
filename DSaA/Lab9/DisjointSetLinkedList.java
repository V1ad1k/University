package Lab9;

public class DisjointSetLinkedList implements DisjointSetDataStructure {

	private class Element{
		int representant;
		int next;
		int length;
		int last;
		public Element(int num) {
			representant=num;
			next=NULL;
			last=num;
			length=1;
		}
	}
	
	private static final int NULL=-1;
	
	Element arr[];
	
	public DisjointSetLinkedList(int size) {
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
		return arr[item].representant;
		}else {
			return -1;
		}
	}

	@Override
	public boolean union(int itemA, int itemB) {
		if(itemA>arr.length||itemA<0||itemB>arr.length||itemB<0) return false;
		if(arr[itemA].length<arr[itemB].length) {
			int temp=itemA;
			itemA=itemB;
			itemB=temp;
		}
		if(arr[itemA].representant!=arr[itemB].representant){
			int newLength=arr[itemA].length+arr[itemB].length;
			int lastOfA=arr[arr[itemA].representant].last;
			int repOfA=arr[itemA].representant;
			int repOfB=arr[itemB].representant;
			arr[lastOfA].next=repOfB;
			arr[repOfB].representant=repOfA;
			arr[repOfA].last=arr[repOfB].last;
			arr[repOfB].last=NULL;
			int index=repOfB;
			while(arr[index].next!=NULL) {
				index=arr[index].next;
				arr[index].representant=repOfA;
			}
			index=repOfA;
			while(arr[index].next!=NULL) {
				arr[index].length=newLength;
				index=arr[index].next;	
			}
			return true;
		}else {
			return false;
		}
	}

	
	@Override
	public String toString() {
		String ret="Disjoint sets as linked list:\n";
		if(arr.length<1) return ret;
		for(int i=0;i<arr.length;i++) {
			if(arr[i].representant==i &&arr[i].last!=NULL) {
				ret=ret+i+", ";
				int index=i;
				while(arr[index].next!=NULL) {
					index=arr[index].next;
					ret=ret+index+", ";
				}
				ret=ret.substring(0, ret.length()-2);
				ret=ret+"\n";
			}
		}
		ret=ret.substring(0, ret.length()-1);
		return ret;
				
	}

}
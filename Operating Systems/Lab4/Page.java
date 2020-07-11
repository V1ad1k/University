package Task4;

public class Page {
    int id;
    boolean bit;
    public Page pageCall;
    public Page(int _page){
        id = _page;
        bit = false;
    }
    public boolean equals(Object obj) {
        ///todo chack what this if statement does
        if (obj != null && obj instanceof Page) {
            Page toCompare = (Page) obj;
            //  if (toCompare.id == null) return false;
            return this.id == toCompare.id;
        }
        return false;
    }
}
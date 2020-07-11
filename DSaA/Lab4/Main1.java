import java.util.*;

interface IList<E> extends Iterable<E> {
    boolean add(E e); // add element to the list on proper position

    void add(int index, E element) throws NoSuchElementException; // not implemented

    void clear(); // delete all elements

    boolean contains(E element); // is list containing an element (equals())

    E get(int index) throws NoSuchElementException; //get element from position

    E set(int index, E element) throws NoSuchElementException; // not implemented

    int indexOf(E element); // where is element (equals())

    boolean isEmpty();

    Iterator<E> iterator();

    ListIterator<E> listIterator() throws UnsupportedOperationException; // for ListIterator

    E remove(int index) throws NoSuchElementException; // remove element from position index

    boolean remove(E e); // remove element

    int size();
}

class TwoWayCycledOrderedListWithSentinel<E extends Comparable<Link>> implements IList<E> {

    private class Element {
        public Element(E e) {
            this.object = e;
        }

        @SuppressWarnings("unused")
		public Element(E e, Element next, Element prev) {
            this.object = e;
            this.next = next;
            this.prev = prev;
        }

        public void setNext(Element next) {
            this.next = next;
        }

        public void setPrev(Element prev) {
            this.prev = prev;
        }

        public void setValue(E object) {
            this.object = object;
        }

        public Element getNext() {
            return next;
        }

        public Element getPrev() {
            return prev;
        }

        public E getValue() {
            return object;
        }

        // add element e after this
        public void addAfter(Element elem) {
            elem.setNext(this.getNext());
            elem.setPrev(this);
            this.getNext().setPrev(elem);
            this.setNext(elem);
        }

        // assert it is NOT a sentinel
        public void remove() {
            if (object != null) {
                this.getNext().setPrev(this.getPrev());
                this.getPrev().setNext(this.getNext());
            }
        }

        E object;
        Element next = null;
        Element prev = null;
    }


    Element sentinel;
    int size;

    private class InnerIterator implements Iterator<E> {
        Element pos;

        public InnerIterator() {
            pos = sentinel;
        }

        @Override
        public boolean hasNext() {
            return pos.getNext() != sentinel;
        }

        @Override
        public E next() {
            pos = pos.getNext();
            return pos.getValue();
        }
    }

    private class InnerListIterator implements ListIterator<E> {
        Element p;

        public InnerListIterator() {
            p = sentinel;
        }

        @Override
        public boolean hasNext() {
            return p.getNext() != sentinel;
        }

        @Override
        public E next() {
            p = p.getNext();
            return p.getValue();
        }

        @Override
        public void add(E arg0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasPrevious() {
            return p != sentinel;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {
            E value = p.getValue();
            p = p.getPrev();
            return value;
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E arg0) {
            throw new UnsupportedOperationException();
        }
    }

    public TwoWayCycledOrderedListWithSentinel() {
        sentinel = new Element(null);
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
        size = 0;
    }

    //@SuppressWarnings("unchecked")
    @Override
    public boolean add(E e) {
        Element newElem = new Element(e);
        Link newValue = (Link) newElem.getValue();
        if (!newValue.getRef().matches("[a-z]\\w*") || newValue.weight <= 0) {
            return false;
        }
        Element actElem = sentinel.getNext();
        if (size == 1) {
            if (newValue.compareTo((Link) actElem.getValue()) >= 0) {
                actElem.addAfter(newElem);
            } else {
                sentinel.addAfter(newElem);
            }
            size++;
            return true;
        }

        if (size > 1 && newValue.compareTo((Link) sentinel.getNext().getValue()) < 0) {
            sentinel.addAfter(newElem);
            size++;
            return true;
        }

        while (actElem != sentinel) {
            Link actValue = (Link) actElem.getValue();
            if (newValue.compareTo(actValue) == 0) {
                while (actElem.getNext() != sentinel && actElem.getNext().getValue().compareTo(newValue) == 0) {
                    actElem = actElem.getNext();
                }
                actElem.addAfter(newElem);
                size++;
                return true;
            }
            if (actElem.getNext() != sentinel && newValue.compareTo(actValue) > 0) {
                actValue = (Link) actElem.getNext().getValue();
                if (newValue.compareTo(actValue) < 0) {
                    actElem.addAfter(newElem);
                    size++;
                    return true;
                }
            }
            actElem = actElem.getNext();
        }
        actElem = sentinel.getPrev();
        actElem.addAfter(newElem);
        size++;
        return true;
    }

    private Element getElement(int index) {
        if (index >= size || index < 0) {
            throw new NoSuchElementException();
        }
        Element currElem = sentinel.getNext();
        while (index > 0 && currElem != sentinel) {
            index--;
            currElem = currElem.getNext();
        }
        if (currElem == sentinel) {
            throw new NoSuchElementException();
        }
        return currElem;
    }

    private Element getElement(E obj) {
        //TODO
        return null;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void clear() {
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    @Override
    public E get(int index) {
        Element actElem = getElement(index);
        return actElem.getValue();
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(E element) {
        int pos = 0;
        Element actElem = sentinel.getNext();
        while (actElem != sentinel) {
            if (actElem.getValue().equals(element)) {
                return pos;
            }
            pos++;
            actElem = actElem.getNext();
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new InnerListIterator();
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        Element actElem = sentinel.getNext();
        if (actElem == sentinel) {
            throw new NoSuchElementException();
        }
        actElem = getElement(index);
        E value = actElem.getValue();
        actElem.remove();
        size--;
        return value;
    }

    @Override
    public boolean remove(E e) {
        Element actElem = sentinel.getNext();
        if (actElem == sentinel) {
            return false;
        }
        while (actElem != sentinel) {
            if (actElem.getValue().equals(e)) {
                actElem.remove();
                size--;
                return true;
            }
            actElem = actElem.getNext();
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }
    
    public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
        Element otherSen = other.sentinel;
        Element curr = sentinel.getNext();
        Element currOther = otherSen.getNext();
        Element temp;
        if (!isEmpty() && !other.isEmpty() && !otherSen.equals(sentinel)) {
          int comparison;
          while (!otherSen.equals(currOther)) {
            comparison = (!sentinel.equals(curr))
                    ? ((Link) curr.getValue()).compareTo((Link) currOther.getValue())
                    : -1;
            if (comparison < 0) {
              temp = currOther.getNext();
              curr.getPrev().addAfter(currOther);
              currOther = temp;
            } else
              curr = curr.getNext();
          }
        } else if (isEmpty() && !other.isEmpty()) {
          while (!otherSen.equals(currOther)) {
            temp = currOther.getNext();
            curr.addAfter(currOther);
            curr = curr.getNext();
            currOther = temp;
          }
        }
        size += other.size();
        other.clear();
      }  

    //@SuppressWarnings({ "unchecked", "rawtypes" })
    public void removeAll(E e) {
        Element actElem = sentinel.getNext();
        while (actElem != sentinel) {
            if (actElem.getValue().equals(e)) {
                actElem.remove();
            }
            actElem = actElem.getNext();
        }
    }

    public String toStringReverse() {
        ListIterator<E> it = new InnerListIterator();
        if (!it.hasNext()) {
            return "";
        }
        StringBuilder retStr = new StringBuilder();
        while (it.hasNext()) {
            it.next();
        }
        int count = 10;
        while (it.hasPrevious()) {
            String seperator = "";
            if (count == 10) {
                seperator = "";
                retStr.append("\n");
            } else if (count != 0) {
                seperator = " ";
            } else {
                seperator = "\n";
            }
            count--;
            retStr.append(seperator).append(it.previous().toString());
        }
        return retStr.toString();

    }
}
class Link implements Comparable<Link> {
    public String ref;
    public int weight;

    public Link(String ref) {
        this.ref = ref;
        weight = 1;
    }

    public Link(String ref, int weight) {
        this.ref = ref.toLowerCase();
        this.weight = weight;
    }

    public String getRef() {
        return ref;
    }

    @Override
    public boolean equals(Object obj) {
        if (ref != null && obj instanceof Link) {
            Link link = (Link) obj;
            if (link.ref == null)
                return false;
            return ref.equals(link.ref);
        }
        return false;
    }

    @Override
    public String toString() {
        return ref + "(" + weight + ")";
    }

    @Override
    public int compareTo(Link another) {
        String thisRef = this.getRef();
        String thatRef = another.getRef();
        int sizeDiff = Math.abs(thisRef.length() - thatRef.length());
        int bound = Math.max(thisRef.length(), thatRef.length()) - sizeDiff;
        for (int i = 0; i < bound; i++) {
            int a = (int) thisRef.charAt(i);
            int b = (int) thatRef.charAt(i);
            if (a > b || a < b) {
                return a - b;
            }
        }
        return thisRef.length() - thatRef.length();
    }
}
class Document {
    public String name;
    public TwoWayCycledOrderedListWithSentinel<Link> link;

    public Document(String name, Scanner scan) {
        this.name = name.toLowerCase();
        link = new TwoWayCycledOrderedListWithSentinel<Link>();
        load(scan);
    }

    public void load(Scanner scan) {
        String input;
        while (!(input = scan.nextLine()).equalsIgnoreCase("eod")) {
            String[] arr = input.split("\\s+");
            for (String word : arr) {
                if (word.equalsIgnoreCase("eod")) {
                    return;
                }
                if (isCorrectLink(word.toLowerCase())) {
                    link.add(createLink(word.toLowerCase().substring(5)));
                }
            }
        }
    }

    public boolean isCorrectLink(String link) {
        return link.toLowerCase().matches("link=[a-z]\\w*") || (link.matches("link=[a-z0-9]*\\([0-9]*\\)") && !link.matches("link=[a-z0-9]*\\(0\\)"));
    }

    public static boolean isCorrectId(String id) {
        return id.toLowerCase().matches("[a-z]\\w*");
    }

    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    // and eventually weight in parenthesis
    public static Link createLink(String link) {
        if (link.matches("[a-z0-9]*\\([0-9]*\\)")) {
            int start = link.indexOf("(") + 1;
            String key = link.toLowerCase().substring(0, start - 1);
            int weight = Integer.parseInt(link.substring(start, link.length() - 1));
            return new Link(key, weight);
        } else {
            return new Link(link.toLowerCase());
        }
    }

    @Override
    public String toString() {
        StringBuffer output = new StringBuffer();
        Iterator<Link> str = link.iterator();
        output.append("Document: " + name);
        int count = 10;
        while (str.hasNext()) {
            String seperator = "";
            if (count == 10) {
                seperator = "";
                output.append("\n");
            } else if (count != 0) {
                seperator = " ";
            } else {
                seperator = "\n";
            }
            count--;
            output.append(seperator).append(str.next().toString());
        }
        return output.toString();
    }

    public String toStringReverse() {
        String retStr = "Document: " + name;
        return retStr + link.toStringReverse();
    }
}

public class Main {

    static Scanner scan; // for input stream


    public static void main(String[] args) {
        System.out.println("START");
        scan = new Scanner(System.in);
        Document[] doc = null;
        int currentDocNo = 0;
        int maxNo = -1;
        boolean halt = false;
        while (!halt) {
            String line = scan.nextLine();
            // empty line and comment line - read next line
            if (line.length() == 0 || line.charAt(0) == '#')
                continue;
            // copy line to output (it is easier to find a place of a mistake)
            System.out.println("!" + line);
            String word[] = line.split(" ");
            // go n - start with array of the length n
            if (word[0].equalsIgnoreCase("go") && word.length == 2) {
                maxNo = Integer.parseInt(word[1]);
                doc = new Document[maxNo];
                continue;
            }
            //ch - change index
            if (word[0].equalsIgnoreCase("ch") && word.length == 2) {
                currentDocNo = Integer.parseInt(word[1]);
                continue;
            }

            // ld documentName
            if (word[0].equalsIgnoreCase("ld") && word.length == 2) {
                if (Document.isCorrectId(word[1]))
                    doc[currentDocNo] = new Document(word[1], scan);
                else
                    System.out.println("incorrect ID");
                continue;
            }
            // ha
            if (word[0].equalsIgnoreCase("ha") && word.length == 1) {
                halt = true;
                continue;
            }
            // clear
            if (word[0].equalsIgnoreCase("clear") && word.length == 1) {
                doc[currentDocNo].link.clear();
                continue;
            }
            // show
            if (word[0].equalsIgnoreCase("show") && word.length == 1) {
                System.out.println(doc[currentDocNo].toString());
                continue;
            }
            // reverse
            if (word[0].equalsIgnoreCase("reverse") && word.length == 1) {
                System.out.println(doc[currentDocNo].toStringReverse());
                continue;
            }
            // size
            if (word[0].equalsIgnoreCase("size") && word.length == 1) {
                System.out.println(doc[currentDocNo].link.size());
                continue;
            }
            // add str
            if (word[0].equalsIgnoreCase("add") && word.length == 2) {
                Link link = Document.createLink(word[1]);
                if (link == null)
                    System.out.println("error");
                else
                    System.out.println(doc[currentDocNo].link.add(link));
                continue;
            }

            // get index
            if (word[0].equalsIgnoreCase("get") && word.length == 2) {
                int index = Integer.parseInt(word[1]);
                try {
                    Link l = doc[currentDocNo].link.get(index);
                    System.out.println(l.ref);
                } catch (NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }

            // index str
            if (word[0].equalsIgnoreCase("index") && word.length == 2) {
                int index = doc[currentDocNo].link.indexOf(new Link(word[1]));
                System.out.println(index);
                continue;
            }
            // remi index
            if (word[0].equalsIgnoreCase("remi") && word.length == 2) {
                int index = Integer.parseInt(word[1]);
                try {
                    Link l = doc[currentDocNo].link.remove(index);
                    System.out.println(l);
                } catch (NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }
            // rem str
            if (word[0].equalsIgnoreCase("rem") && word.length == 2) {
                System.out.println(doc[currentDocNo].link.remove(new Link(word[1])));
                continue;
            }
            // remall str
            if (word[0].equalsIgnoreCase("remall") && word.length == 2) {
                doc[currentDocNo].link.removeAll(new Link(word[1]));
                continue;
            }
            // addl <indexOfListArray>
            if (word[0].equalsIgnoreCase("addl") && word.length == 2) {
                int number = Integer.parseInt(word[1]);
                doc[currentDocNo].link.add(doc[number].link);
                continue;
            }
            System.out.println("Wrong command");
        }
        System.out.println("END OF EXECUTION");
        scan.close();

    }


}
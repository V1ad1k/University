package lab4;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

class TwoWayCycledOrderedListWithSentinel<E> implements IList<E> {

	  // "Node" inner class for the list
	  private class Element {
	    Element(E e) {
	      this.object = e;
	    }

	    Element(E e, Element next, Element prev) {
	      this.object = e;
	      this.next = next;
	      this.prev = prev;
	    }

	    private Element getNext() {
	      return next;
	    }

	    private Element getPrev() {
	      return prev;
	    }

	    private E getValue() { return object; }

	    private void setNext(Element el) {
	      next = el;
	    }

	    private void setPrev(Element el) {
	      prev = el;
	    }

	    void connectSelf() {
	      setNext(this);
	      setPrev(this);
	    }

	    // add element e after this
	    void addAfter(Element elem) {
	      elem.setNext(getNext());
	      elem.setPrev(this);
	      getNext().setPrev(elem);
	      setNext(elem);
	    }

	    // assert it is NOT a sentinel
	    public void remove() {
	      getPrev().setNext(getNext());
	      getNext().setPrev(getPrev());
	    }

	    E object;
	    Element next = null;
	    Element prev = null;
	  }

	  private Element sentinel;
	  private int size;

	  // the same as in the lecture W02
	  private class InnerIterator implements Iterator<E> {
	    Element _current = sentinel;

	    @Override
	    public boolean hasNext() {
	      return !_current.getNext().equals(sentinel);
	    }

	    @Override
	    public E next() {
	      _current = _current.getNext();
	      return _current.getValue();
	    }
	  }
	  // the same as in the lecture W02
	  private class InnerListIterator implements ListIterator<E> {
	    Element _current = sentinel;
	    boolean wasNext = false;

	    @Override
	    public boolean hasNext() {
	      return !_current.getNext().equals(sentinel);
	    }

	    @Override
	    public E next() {
	      wasNext = true;
	      _current = _current.getNext();
	      return _current.getValue();
	    }

	    @Override
	    public void add(E arg0) {
	      throw new UnsupportedOperationException();
	    }

	    @Override
	    public boolean hasPrevious() {
	      return !_current.equals(sentinel);
	    }

	    @Override
	    public int nextIndex() {
	      throw new UnsupportedOperationException();
	    }

	    @Override
	    public E previous() {
	      wasNext = false;
	      E tempVal = _current.getValue();
	      _current = _current.getPrev();
	      return tempVal;
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

	  // Standard constructor for a list with a sentinel
	  TwoWayCycledOrderedListWithSentinel() {
	    sentinel = new Element(null);
	    sentinel.connectSelf();
	    size = 0;
	  }

	  @Override
	  public boolean add(E e) {
	    Element added = new Element(e);
	    size++;
	    if (isEmpty()) {
	      sentinel.addAfter(added);
	      return true;
	    }
	    int comparison;
	    Element curr = sentinel.getNext();
	    while (!sentinel.equals(curr)) {
	      comparison = ((Link) curr.getValue()).compareTo((Link) e);
	      if (comparison < 0)
	        break;
	      curr = curr.getNext();
	    }
	    curr.getPrev().addAfter(added);
	    return true;
	  }

	  /* The "Divide and Conquer" approach is used - writing recursive functions
	  *  which have running time O(n*lg(n))
	  */

	  private Element getElement(int index, Element current) {
	    return (index > 0)
	            ? getElement(index - 1, current.getNext())
	            : current.getNext();
	  }

	  private Element getElement(int index) {
	    // copied from the previous lab
	    if (index < 0 || index >= size)
	      throw new NoSuchElementException();
	    return getElement(index, sentinel);
	  }

	  private Element getElement(E query, Element current) {
	    return (sentinel.equals(current)) ? null
	            : (current.getValue().equals(query)) ? current
	            : getElement(query, current.getNext());
	  }

	  private Element getElement(E obj) {
	    if (obj == null || isEmpty())
	      throw new NoSuchElementException();
	    return getElement(obj, sentinel.getNext());
	  }

	  @Override
	  public void add(int index, E element) {
	    throw new UnsupportedOperationException();
	  }

	  @Override
	  public void clear() {
	    sentinel.connectSelf();
	    size = 0;
	  }

	  @Override
	  public boolean contains(E val) {
	    return indexOf(val)!=-1;
	  }

	  @Override
	  public E get(int index) {
	    return getElement(index).getValue();
	  }

	  @Override
	  public E set(int index, E element) {
	    throw new UnsupportedOperationException();
	  }

	  @Override
	  public int indexOf(E val) {
	    // copied from the previous lab
	    int ind = 0;
	    Element tail = sentinel.getNext();
	    while (val != null && !sentinel.equals(tail)) {
	      if (tail.getValue().equals(val))
	        return ind;
	      tail = tail.getNext();
	      ind++;
	    }
	    return -1;
	  }

	  @Override
	  public boolean isEmpty() {
	    return size == 0 || sentinel.getNext() == null;
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
	    // copied from the previous lab
	    Element rem = getElement(index);
	    rem.remove();
	    size--;
	    return rem.getValue();
	  }

	  @Override
	  public boolean remove(E e) {
	    Element rem = getElement(e);
	    if (rem==null)
	      return false;
	    rem.remove();
	    size--;
	    return true;
	  }

	  @Override
	  public int size() { return size; }

	  public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
	    Element curr = sentinel.getNext();
	    Element currOther = other.sentinel.getNext();
	    Element temp;
	    if (!other.isEmpty() && !other.sentinel.equals(sentinel)) {
	      while (!other.sentinel.equals(currOther)) {
	        int comparison = (!sentinel.equals(curr))
	                ? ((Link) curr.getValue()).compareTo((Link) currOther.getValue())
	                : -1;
	        if (comparison < 0) {
	          temp = currOther.getNext();
	          curr.getPrev().addAfter(currOther);
	          currOther = temp;
	        } else
	          curr = curr.getNext();
	      }
	    } 
	    size += other.size();
	    other.clear();
	  }

	  void removeAll(E e) {
	    int comparison = Integer.MAX_VALUE;
	    Element curr = sentinel.getNext();
	    while (e!=null && comparison>=0 && !sentinel.equals(curr)) {
	      comparison = ((Link) curr.getValue()).compareTo((Link) e);
	      if (comparison==0) {
	        curr.remove();
	        size--;
	      }
	      curr = curr.getNext();
	    }
	  }

	}

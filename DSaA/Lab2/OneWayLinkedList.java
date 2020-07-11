package Lab_2;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

class OneWayLinkedList<E> implements IList<E> {

	  private class Element {
	    public Element(E e) {
	      this.object = e;
	    }
	    
	    public Element getNext() { return next; }
	    public void setNext(Element next) { this.next = next; }
	    public E getValue() { return object; }
	    public void setValue(E obj) { object = obj; }

	    E object;
	    Element next = null;
	  }

	  Element sentinel;

	  private class InnerIterator implements Iterator<E> {
	    Element Elem;

	    public InnerIterator() {
	     Elem = sentinel;
	    }

	    @Override	
	    public boolean hasNext() {
	      return Elem.getNext() != null;
	    }

	    @Override
	    public E next() {
	      Elem = Elem.getNext();
	      return Elem.getValue();
	    }
	  }

	  public OneWayLinkedList() {
	    // make a sentinel
	    sentinel = new Element(null);
	  }

	  @Override
	  public Iterator<E> iterator() {
	    return new InnerIterator();
	  }

	  @Override
	  public ListIterator<E> listIterator() {
	    throw new UnsupportedOperationException();
	  }

	  @Override
	  public boolean add(E e) {
	    Element newElement = new Element(e);
	    Element tail = sentinel;
	    while (tail.getNext() != null)
	      tail = tail.getNext();
	    tail.setNext(newElement);
	    return true;
	  }

	  @Override
	  public void add(int index, E element) throws NoSuchElementException {
	    if (index < 0)
	      throw new NoSuchElementException();
	    Element newElement = new Element(element);
	    if (index == 0) {
	    	newElement.setNext(sentinel.getNext());
	      sentinel.setNext(newElement);
	    } else {
	      Element current = getElement(index - 1);
	      newElement.setNext(current.getNext());
	      current.setNext(newElement);
	    }
	  }

	  @Override
	  public void clear() {
	    sentinel.setNext(null);
	  }

	  @Override
	  public boolean contains(E element) {
	    return indexOf(element)!=-1;
	  }

	  @Override
	  public E get(int index) throws NoSuchElementException {
	    if (index < 0 || isEmpty())
	      throw new NoSuchElementException();
	    return getElement(index).getValue();
	  }

	  @Override
	  public E set(int index, E element) throws NoSuchElementException {
	    if (index < 0 || isEmpty())
	      throw new NoSuchElementException();
	    Element actElem = getElement(index);
	    E elemData = actElem.getValue();
	    actElem.setValue(element);
	    return elemData;
	  }

	  public Element getElement(int index) {
	    Element searchE = sentinel.getNext();
	    while (index > 0 && searchE!=null) {
	      searchE = searchE.getNext();
	      index--;
	    }
	    if (searchE==null)
	      throw new NoSuchElementException();
	    return searchE;
	  }

	  @Override
	  public int indexOf(E element) {
	    int counter = 0;
	    Iterator<E> iterator = iterator();
	    while (iterator.hasNext()) {
	      if (iterator.next().equals(element))
	        return counter;
	      counter++;
	    }
	    return -1;
	  }

	  @Override
	  public boolean isEmpty() {
	    return sentinel.getNext() == null;
	  }

	  @Override
	  public E remove(int index) throws NoSuchElementException {
	    Element removeElem;
	    if (index < 0 || isEmpty())
	      throw new NoSuchElementException();
	    else if (index == 0) {
	      removeElem = sentinel.getNext();
	      sentinel.setNext(removeElem.getNext());
	    } else {
	      removeElem = getElement(index);
	      getElement(index - 1).setNext(removeElem.getNext());
	    }
	    
	    return removeElem.getValue();
	  }

	  @Override
	  public boolean remove(E e) {
	        if (sentinel.getNext() == null) {
	            return false;
	        }
//	        if (sentinel.getNext().getValue().equals(e)) {
//	            sentinel.setNext(sentinel.getNext().getNext());
//	            return true;
	       // }
	        Element check = sentinel;
	        while (check.getNext() != null && !(check.getNext().getValue().equals(e))) {
	            check = check.getNext();
	        }
	        if (check.getNext() == null) {
	            return false;
	        }
	        check.setNext(check.getNext().getNext());
	        return true;
	    }

	  @Override
	  public int size() {
	    int counter = 0;
	    Iterator<E> iterator = iterator();
	    while (iterator.hasNext()) {
	      iterator.next();
	      counter++;
	    }
	    return counter;
	  }

	}
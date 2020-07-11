package lab3_1;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


class TwoWayLinkedListWithHead<E> implements IList<E> {

	private class Element {
		E object;
		Element next;
		Element prev;

		Element(E e) { this.object = e; }
		@SuppressWarnings("unused")
		Element(E e, Element next, Element prev) {
			this.object = e;
			this.next = next;
			this.prev = prev;
		}

		E getValue() { return object; }
		Element getNext() { return next; }
		Element getPrev() { return prev; }

		void setValue(E object) { this.object = object; }
		void setNext(Element next) { this.next = next; }
		void setPrev(Element prev) { this.prev = prev; }

		void insertAfter(Element elem) {
			elem.setNext(getNext());
			elem.setPrev(this);
			getNext().setPrev(elem);
			setNext(elem);
		}

		void insertBefore(Element elem) {
			elem.setNext(this);
			elem.setPrev(getPrev());
			getPrev().setNext(elem);
			setPrev(elem);
			if (head.equals(this))
				head = getPrev();
		}

		private void connectSelf() {
			setNext(this);
			setPrev(this);
		}

		void remove() {
			getNext().setPrev(getPrev());
			getPrev().setNext(getNext());
			if (head.equals(this)) {
				head = getNext();
			}
		}
	}

	private Element head;
	private int size;

	private class InnerIterator implements Iterator<E> {
		Element currElem = head;
		int currInd = 0;

		@Override
		public boolean hasNext() {
			return currElem!=null && currInd<size;
		}

		@Override
		public E next() {
			if (currElem==null || currInd>=size)
				throw new NoSuchElementException();
			E curr = currElem.getValue();
			currElem = currElem.getNext();
			currInd++;
			return curr;
		}
	}

	private class InnerListIterator implements ListIterator<E> {
		Element posElem = head;
		int posInd = 0;
		boolean wasNext = false;

		@Override
		public boolean hasNext() {
			return posElem != null && posInd<size;
		}

		@Override
		public E next() {
			if (posElem==null)
				throw new NoSuchElementException();
			E posValue = posElem.getValue();
			posElem = posElem.getNext();
			posInd++;
			wasNext = true;
			return posValue;
		}

		@Override
		public boolean hasPrevious() {
			return posElem!=null
					&& posElem.getPrev()!=null && posInd>0;
		}

		@Override
		public E previous() {
			if (posElem==null || posElem.getPrev()==null)
				throw new NoSuchElementException();
			posElem = posElem.getPrev();
			posInd--;
			wasNext = false;
			return posElem.getValue();
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
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
		public void set(E e) {
			if (wasNext && posElem.getPrev()!=null)
				posElem.getPrev().setValue(e);
			else if (!wasNext && posElem!=null)
				posElem.setValue(e);
		}

		@Override
		public void add(E e) {
			throw new UnsupportedOperationException();
		}
	}

	TwoWayLinkedListWithHead() {
		head = null;
		size = 0;
	}

	@Override
	public boolean add(E val) {
		Element newElem = new Element(val);
		if (isEmpty()) {
			head = newElem;
			head.connectSelf();
		} else
			head.getPrev().insertAfter(newElem);
		size++;
		return true;
	}

	@Override
	public void add(int index, E val) {
		Element newElem = new Element(val);
		if (isEmpty() && index == 0) {
			head = newElem;
			head.connectSelf();
		} else if (index==size)
			head.getPrev().insertAfter(newElem);
		else
			getElement(index).insertBefore(newElem);
		size++;
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public boolean contains(E val) {
		return indexOf(val)!=-1;
	}

	private Element getElement(int index, Element current) {
		return (index>0)
				? getElement(index-1, current.getNext())
						: current;
	}

	private Element getElement(int index) {
		if (index<0 || index>=size)
			throw new NoSuchElementException();
		return getElement(index, head);
	}

	@Override
	public E get(int index) {
		return getElement(index).getValue();
	}

	@Override
	public E set(int index, E val) {
		Element elem2modify = getElement(index);
		E oldValue = elem2modify.getValue();
		elem2modify.setValue(val);
		return oldValue;
	}

	@Override
	public int indexOf(E val) {
		int ind = 0;
		boolean found = false;
		Element tail = head;
		while (tail!=null && val!=null && ind<size) {
			if (tail.getValue().equals(val)) {
				found = true;
				break;
			}
			tail = tail.getNext();
			ind++;
		}
		return (found) ? ind : -1;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
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
		Element elem2remove = getElement(index);
		elem2remove.remove();
		size--;
		return elem2remove.getValue();
	}
	public void removeodd() { 
		if (head == null) return; 
		  
		Element prev = head; 
		Element now = head.getNext(); 
		  
		while (prev != null && now != null){           
			prev.next = now.getNext(); 		  
			now = null; 
			prev = prev.getNext(); 
		    if (prev != null) now = prev.getNext(); 
		}
	}

	@Override
	public boolean remove(E val) {
		int removeInd = indexOf(val);
		if (removeInd!=-1) {
			getElement(removeInd).remove();
			size--;
			return true;
		} else
			return false;
	}

	@Override
	public int size() { return size; }

	public String toString() {
		Iterator<E> it = new InnerIterator();
		StringBuilder retStr = new StringBuilder();
		while (it.hasNext()) {
			Link tempLink = (Link) it.next();
			retStr.append(String.format("%n%s", tempLink.getRef()));
		}
		return retStr.toString();
	}

	String toStringReverse() {
		ListIterator<E> iter = new InnerListIterator();
		while (iter.hasNext())
			iter.next();
		StringBuilder retStr = new StringBuilder();
		while (iter.hasPrevious()) {
			Link tempLink = (Link) iter.previous();
			retStr.append(String.format("%n%s", tempLink.getRef()));
		}
		return retStr.toString();
	}

	public void add(TwoWayLinkedListWithHead<E> other) {
		if (!isEmpty() && !other.isEmpty() && !other.head.equals(head)) {
			Element oldTail = head.getPrev();
			oldTail.setNext(other.head);
			Element newHead = oldTail.getNext();
			Element newTail = newHead.getPrev();
			newHead.setPrev(oldTail);
			newTail.setNext(head);
			head.setPrev(newTail);
		} else if (isEmpty() && !other.isEmpty())
			head = other.head;
		size += other.size();
		other.clear();
	}

}
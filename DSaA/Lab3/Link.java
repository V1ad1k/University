package lab3_1;

class Link {

	String ref;

	Link(String ref) {
		this.ref = ref;
	}

	String getRef() { return ref; }
	public boolean setRef(String newRef) {
		if (newRef != null) {
			ref = newRef;
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Link) {
			Link newLink = (Link) obj;
			return newLink.getRef().equalsIgnoreCase(ref);
		}
		return false;
	}
}
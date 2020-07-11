package lab4;

class Link implements Comparable<Link> {

	  String ref;
	  private int weight;

	  Link(String ref) {
	    this.ref = ref;
	    weight = 1;
	  }

	  Link(String ref, int weight) {
	    this.ref = ref;
	    this.weight = weight;
	  }

	  private String getRef() { return ref; }

	  @Override
	  public boolean equals(Object obj) {
	    if (obj instanceof Link) {
	      Link receivedLink = (Link) obj;
	      return receivedLink.getRef().equalsIgnoreCase(ref);
	    }
	    return false;
	  }

	  @Override
	  public String toString() {
	    return ref + "(" + weight + ")";
	  }

	  @Override
	  public int compareTo(Link another) {
	    return another.getRef().compareTo(ref);
	  }

	}
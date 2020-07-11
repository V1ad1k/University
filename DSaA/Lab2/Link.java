package Lab_2;

class Link {

	  public String ref;

	  public Link(String ref) {
	    this.ref = ref;
	  }

	  public String getRef() { return ref; }

	  @Override
	  public boolean equals(Object object) {
	    if (!(object instanceof Link))
	      return false;
	    Link temp = (Link) object;
	    return ref.equals(temp.ref);
	  }

	  public boolean equals(Link link) {
	    return ref.equals(link.ref);
	  }

	}

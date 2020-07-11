package Lab_2;

import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

class Document {

	  public String name;
	  public OneWayLinkedList<Link> links;

	  public Document(String name, Scanner sc) {
	    this.name = name;
	    links = new OneWayLinkedList<Link>();
	    load(sc);
	  }

	  public void load(Scanner sc) {
	    String inp;
	    while (!(inp = sc.nextLine()).equalsIgnoreCase("eod")) {
	      String[] arr = inp.split("\\s+");
	      for (String word : arr) {
	        if (correctLink(word)) {
	          Link temp = new Link(word.substring(5).toLowerCase());
	          links.add(temp);
	        }
	      }
	    }
	  }

	  private static boolean correctLink(String link) {
	    return link.toLowerCase().matches("link=[a-z]\\w*");
	  }

	  @Override
	  public String toString() {
		String result = "";
	    Iterator<Link> str = links.iterator();
	    result+=("Document: "+name);
	    while (str.hasNext()) {
	      result+=("\n" + str.next().getRef());
	    }
	    return result;
	  }

	}
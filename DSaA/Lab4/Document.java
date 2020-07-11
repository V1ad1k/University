package lab4;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Document {

	  private String name;
	  private static Pattern mainPattern = Pattern.compile("link=([a-z][a-z_0-9]*)(?:\\(.*\\))?$");
	  private static Pattern weightPattern = Pattern.compile("link=.+\\(((\\d+)|(.*))\\)$");
	  TwoWayCycledOrderedListWithSentinel<Link> links;

	  Document(String name, Scanner scan) {
	    this.name = name.toLowerCase();
	    links = new TwoWayCycledOrderedListWithSentinel<>();
	    load(scan);
	  }

	  private void load(Scanner scan) {
	    String inputLine;
	    while(!(inputLine = scan.nextLine()).equalsIgnoreCase("eod")) {
	      String[] words = inputLine.split("\\s+");
	      for(String word : words) {
	        Link newLink = createLink(word.toLowerCase(), false);
	        if (newLink!=null)
	          links.add(newLink);
	      }
	    }
	  }

	  static boolean isCorrectId(String id) {
	    return id.matches("^[A-Za-z][A-Za-z\\d]*$");
	  }

	  // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	  // and eventually weight in parenthesis
	  private static Link createLink(String link, boolean isFromMain) {
	    link = (isFromMain) ? "link="+link : link;
	    Matcher mainMatch = mainPattern.matcher(link);
	    if (mainMatch.find()) {
	      Matcher weightMatch = weightPattern.matcher(link);
	      link = mainMatch.group(1);
	      boolean hasBrackets = weightMatch.find();
	      if (hasBrackets && weightMatch.group(2)!=null)
	        return new Link(link, Integer.parseInt(weightMatch.group(2)));
	      else if (!hasBrackets)
	        return new Link(link);
	    }
	    return null;
	  }

	  static Link createLink(String link) {
		    return createLink(link, true);
		  }

		  @Override
		  public String toString() {
		    StringBuilder retStr = new StringBuilder("Document: " + name);
		    Iterator<Link> iter = links.iterator();
		    int splitCounter = 0;
		    while (iter.hasNext()) {
		      String temp = ((splitCounter%10==0) ? "\n" : " ")+iter.next();
		      splitCounter++;
		      retStr.append(temp);
		    }
		    return retStr.toString();
		  }

		  String toStringReverse() {
		    StringBuilder retStr = new StringBuilder("Document: " + name);
		    ListIterator<Link> iter = links.listIterator();
		    int splitCounter = 0;
		    while (iter.hasNext())
		      iter.next();
		    while (iter.hasPrevious()) {
		      String temp = ((splitCounter%10==0) ? "\n" : " ")+iter.previous();
		      splitCounter++;
		      retStr.append(temp);
		    }
		    return retStr.toString();
		  }

		}

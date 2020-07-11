package lab3_1;

import java.util.Scanner;

class Document {

	private String name;
	TwoWayLinkedListWithHead<Link> links;

	Document(String name, Scanner scan) {
		this.name = name;
		links = new TwoWayLinkedListWithHead<>();
		load(scan);
	}

	private void load(Scanner scan) {
		String inputLine;
		while(!(inputLine = scan.nextLine()).equalsIgnoreCase("eod")){
			String[] wordsArray = inputLine.split("\\s+");
			for(String word : wordsArray){
				if(correctLink(word)) {
					Link newLink = new Link (word.substring(5).toLowerCase());
					links.add(newLink);
				}
			}
		}
	}

	private static boolean correctLink(String link) {
		return link.toLowerCase().matches("link=[A-Za-z][A-Za-z_0-9]*$");
	}

	@Override
	public String toString() { return "Document: "+name+links; }
	String toStringReverse() {
		return "Document: "+name+links.toStringReverse();
	}

}
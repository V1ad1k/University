package Lab7;

import java.util.Iterator;
import java.util.Scanner;
class Document implements IWithName {
    public String name;
    public TwoWayCycledOrderedListWithSentinel<Link> link;

    public Document(String name, Scanner scan) {
        this.name = name.toLowerCase();
        link = new TwoWayCycledOrderedListWithSentinel<Link>();
        load(scan);
    }

    public Document(String s) {
        this.name = s;
        link = new TwoWayCycledOrderedListWithSentinel<>();
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
        output.append("Document: ").append(name);
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int[] sequence = {7, 11, 13, 17, 19};

        if (getName().length() == 1) {
            return getName().codePointAt(0);
        }

        int total = 0;
        for (int i = 0; i < getName().length() - 1; i++) {
            if (i == 0) {
                total += getName().codePointAt(0) * sequence[0] + getName().codePointAt(1);
            } else {
                total = total * sequence[i % sequence.length] + getName().codePointAt(i + 1);
            }
        }
        return total;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Document)) {
            return false;
        }
        Document temp = (Document) obj;
        if (this.getName() == null || temp.getName() == null) {
            return false;
        }
        return temp.getName().equals(this.getName());
    }
}

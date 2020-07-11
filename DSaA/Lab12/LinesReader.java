package Lab12;

import java.util.Scanner;

public class LinesReader {
    String concatLines(int howMany, Scanner scanner) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < howMany && scanner.hasNext(); i++) {
            output.append(scanner.next());
        }
        return output.toString();
    }

}
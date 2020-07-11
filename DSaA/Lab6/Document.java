package Lab6;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

class Document {
    public String name;
    public TwoWayCycledOrderedListWithSentinel<Link> link;

    public Document(String name, Scanner scan) {
        this.name = name.toLowerCase();
        link = new TwoWayCycledOrderedListWithSentinel<Link>();
        load(scan);
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
        output.append("Document: " + name);
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

    public int[] getWeights() {
        Iterator<Link> list = link.iterator();
        int[] weights = new int[link.size()];
        int counter = 0;
        while (list.hasNext()) {
            weights[counter] = list.next().getWeight();
            counter++;
        }
        return weights;
    }

    public static void showArray(int[] arr) {
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            output.append(arr[i]).append(" ");
        }
        output.setLength(output.length() - 1);
        System.out.println(output.toString());
    }

    public void bubbleSort(int[] arr) {
        showArray(arr);
        for (int i = 1; i < arr.length; i++) {
            for (int j = arr.length - 2; j >= 0; j--) {
                int right = arr[j + 1];
                int left = arr[j];
                if (left > right) {
                    arr[j] = right;
                    arr[j + 1] = left;
                }
            }
            showArray(arr);
        }
    }

    public void insertSort(int[] arr) {
        showArray(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = i; j < arr.length && arr[j - 1] > arr[j]; j++) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }
            showArray(arr);
        }
    }

    public void selectSort(int[] arr) {
        showArray(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            int index = i;
            int bigger = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] > bigger) {
                    bigger = arr[j];
                    index = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
            showArray(arr);
        }
    }

    public static void merge(int[] arr, int[] temp, int lo, int mid, int hi) {
        if (mid >= arr.length) {
            return;
        }
        if (hi > arr.length) {
            hi = arr.length;
        }
        int i = lo;
        int j = mid;
        for (int k = lo; k < hi; k++) {
            if (i == mid) {
                temp[k] = arr[j++];
            } else if (j == hi) {
                temp[k] = arr[i++];
            } else if (arr[j] < arr[i]) {
                temp[k] = arr[j++];
            } else {
                temp[k] = arr[i++];
            }
        }
        if (hi - lo >= 0) System.arraycopy(temp, lo, arr, lo, hi - lo);
        //for (int k = lo; k < hi; k++) arr[k] = temp[k];
    }

    public void iterativeMergeSort(int[] arr) {
        int[] temp = new int[arr.length];
        showArray(arr);
        for (int i = 1; i < arr.length; i *= 2) {
            for (int j = 0; j < arr.length; j += 2 * i) {
                merge(arr, temp, j, i + j, j + 2 * i);
            }
            showArray(arr);
        }
    }

    public static void countSort(int[] arr, int exp) {
        int[] output = new int[arr.length];
        int[] digits = new int[10];
        Arrays.fill(digits, 0);

        for (int value : arr) {
            digits[(value / exp) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            digits[i] += digits[i - 1];
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            output[digits[(arr[i] / exp) % 10] - 1] = arr[i];
            digits[(arr[i]) / exp % 10]--;
        }
        System.arraycopy(output, 0, arr, 0, arr.length);
        showArray(output);
    }

    public void radixSort(int[] arr) {
        showArray(arr);
        int m = Arrays.stream(arr).max().getAsInt();
        for (int exp = 1; exp < 1000; exp *= 10) {
            countSort(arr, exp);
        }
    }
}

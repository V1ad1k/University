package Lab12;


import java.util.LinkedList;


public class KMP implements IStringMatcher {

    private void prefix(String pattern, int m, int[] arr) {
        int length = 0;
        int i = 1;
        arr[0] = 0;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                arr[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = arr[length - 1];
                } else {
                    arr[i] = length;
                    i++;
                }
            }
        }
    }

    @Override
    public LinkedList<Integer> validShifts(String pattern, String text) {
        LinkedList<Integer> result = new LinkedList<>();
        int k = text.length();
        int m = pattern.length();
        int[] arr = new int[m];
        prefix(pattern, m, arr);

        int i = 0;
        int j = 0;
        while (i < k) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            if (j == m) {
                result.add(i - j);
                j = arr[j - 1];
            } else if (i < k && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = arr[j - 1];
                } else {
                    i++;
                }
            }
        }
        return result;
    }
}
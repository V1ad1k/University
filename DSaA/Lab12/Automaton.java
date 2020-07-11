package Lab12;


import java.util.LinkedList;


public class Automaton implements IStringMatcher {
    private static final int noOfChars = 256;

    private static int getNextState(String pattern, int M, int state, int x) {
        char[] patt = pattern.toCharArray();
        if (state < M && x == patt[state]) {
            return state + 1;
        }
        int ns, i;
        for (ns = state; ns > 0; ns--) {
            if (patt[ns - 1] == x) {
                for (i = 0; i < ns - 1; i++)
                    if (patt[i] != patt[state - ns + 1 + i])
                        break;
                if (i == ns - 1)
                    return ns;
            }
        }

        return 0;
    }

    private static void computeTF(String pattern, int M, int[][] TF) {
        for (int state = 0; state <= M; state++)
            for (int x = 0; x < noOfChars; ++x)
                TF[state][x] = getNextState(pattern, M, state, x);
    }


    @Override
    public LinkedList<Integer> validShifts(String pattern, String text) {
        LinkedList<Integer> result = new LinkedList<>();
        char[] txt = text.toCharArray();
        int M = pattern.length();
        int N = text.length();
        int[][] TF = new int[M + 1][noOfChars];
        computeTF(pattern, M, TF);
        int i, state = 0;
        for (i = 0; i < N; i++) {
            state = TF[state][txt[i]];
            if (state == M) {
                result.add(i - M + 1);
            }
        }
        return result;
    }

}
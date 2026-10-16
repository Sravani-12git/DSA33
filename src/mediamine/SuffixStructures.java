package mediamine;

import java.util.*;

public class SuffixStructures {

    // ================= SUFFIX ARRAY =================

    public static int[] buildSuffixArray(String text) {

        int n = text.length();

        Integer[] suffixes = new Integer[n];

        for (int i = 0; i < n; i++) {
            suffixes[i] = i;
        }

        Arrays.sort(suffixes, (a, b) ->
                text.substring(a).compareTo(text.substring(b)));

        int[] suffixArray = new int[n];

        for (int i = 0; i < n; i++) {
            suffixArray[i] = suffixes[i];
        }

        return suffixArray;
    }

    // ================= LCP / KASAI =================

    public static int[] buildLCP(
            String text,
            int[] suffixArray) {

        int n = text.length();

        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[suffixArray[i]] = i;
        }

        int[] lcp = new int[n];

        int k = 0;

        for (int i = 0; i < n; i++) {

            if (rank[i] == n - 1) {
                k = 0;
                continue;
            }

            int j = suffixArray[rank[i] + 1];

            while (i + k < n &&
                   j + k < n &&
                   text.charAt(i + k) == text.charAt(j + k)) {

                k++;
            }

            lcp[rank[i]] = k;

            if (k > 0) {
                k--;
            }
        }

        return lcp;
    }

    // ================= COMMON PHRASE =================

    public static String longestCommonSubstring(
            String first,
            String second) {

        int[][] dp =
                new int[first.length() + 1][second.length() + 1];

        int maxLength = 0;
        int endPosition = 0;

        for (int i = 1; i <= first.length(); i++) {

            for (int j = 1; j <= second.length(); j++) {

                if (first.charAt(i - 1) ==
                    second.charAt(j - 1)) {

                    dp[i][j] =
                            dp[i - 1][j - 1] + 1;

                    if (dp[i][j] > maxLength) {

                        maxLength = dp[i][j];
                        endPosition = i;
                    }
                }
            }
        }

        return first.substring(
                endPosition - maxLength,
                endPosition);
    }

    // ================= SUFFIX AUTOMATA STYLE RETRIEVAL =================

    public static boolean containsSubstring(
            String text,
            String query) {

        return text.toLowerCase()
                   .contains(query.toLowerCase());
    }
}
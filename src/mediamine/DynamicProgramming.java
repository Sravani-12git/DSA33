package mediamine;

import java.util.*;

public class DynamicProgramming {

    // ================= LEVENSHTEIN =================

    public static int levenshtein(
            String a,
            String b) {

        int[][] dp =
                new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= b.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= a.length(); i++) {

            for (int j = 1; j <= b.length(); j++) {

                int cost =
                        a.charAt(i - 1) ==
                        b.charAt(j - 1) ? 0 : 1;

                dp[i][j] = Math.min(
                        Math.min(
                                dp[i - 1][j] + 1,
                                dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost
                );
            }
        }

        return dp[a.length()][b.length()];
    }

    // ================= DAMERAU LEVENSHTEIN =================

    public static int damerauLevenshtein(
            String a,
            String b) {

        int[][] dp =
                new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= b.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= a.length(); i++) {

            for (int j = 1; j <= b.length(); j++) {

                int cost =
                        a.charAt(i - 1) ==
                        b.charAt(j - 1) ? 0 : 1;

                dp[i][j] = Math.min(
                        Math.min(
                                dp[i - 1][j] + 1,
                                dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost
                );

                if (i > 1 && j > 1 &&
                    a.charAt(i - 1) == b.charAt(j - 2) &&
                    a.charAt(i - 2) == b.charAt(j - 1)) {

                    dp[i][j] =
                            Math.min(
                                    dp[i][j],
                                    dp[i - 2][j - 2] + 1);
                }
            }
        }

        return dp[a.length()][b.length()];
    }

    // ================= BITMASK DP =================

    public static int bestCategorySelection(
            int[] values,
            int budget) {

        int n = values.length;

        int best = 0;

        for (int mask = 0;
             mask < (1 << n);
             mask++) {

            int sum = 0;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {

                    sum += values[i];
                }
            }

            if (sum <= budget) {
                best = Math.max(best, sum);
            }
        }

        return best;
    }

    // ================= MATRIX CHAIN =================

    public static int matrixChainCost(int[] dimensions) {

        int n = dimensions.length - 1;

        int[][] dp = new int[n][n];

        for (int length = 2;
             length <= n;
             length++) {

            for (int i = 0;
                 i < n - length + 1;
                 i++) {

                int j = i + length - 1;

                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i;
                     k < j;
                     k++) {

                    int cost =
                            dp[i][k]
                            + dp[k + 1][j]
                            + dimensions[i]
                            * dimensions[k + 1]
                            * dimensions[j + 1];

                    dp[i][j] =
                            Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[0][n - 1];
    }
}
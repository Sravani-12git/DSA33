package mediamine;

import java.util.*;

public class StringAlgorithms {

    // ================= KMP =================

    public static List<Integer> kmpSearch(String text, String pattern) {

        List<Integer> positions = new ArrayList<>();

        if (pattern.length() == 0) {
            return positions;
        }

        int[] lps = buildLPS(pattern);

        int i = 0;
        int j = 0;

        while (i < text.length()) {

            if (Character.toLowerCase(text.charAt(i)) ==
                Character.toLowerCase(pattern.charAt(j))) {

                i++;
                j++;

                if (j == pattern.length()) {

                    positions.add(i - j);

                    j = lps[j - 1];
                }

            } else {

                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return positions;
    }

    private static int[] buildLPS(String pattern) {

        int[] lps = new int[pattern.length()];

        int len = 0;

        for (int i = 1; i < pattern.length();) {

            if (pattern.charAt(i) == pattern.charAt(len)) {

                len++;
                lps[i] = len;
                i++;

            } else {

                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    // ================= Z FUNCTION =================

    public static int[] zFunction(String text) {

        int n = text.length();

        int[] z = new int[n];

        int left = 0;
        int right = 0;

        for (int i = 1; i < n; i++) {

            if (i <= right) {
                z[i] = Math.min(right - i + 1, z[i - left]);
            }

            while (i + z[i] < n &&
                   text.charAt(z[i]) == text.charAt(i + z[i])) {

                z[i]++;
            }

            if (i + z[i] - 1 > right) {

                left = i;
                right = i + z[i] - 1;
            }
        }

        return z;
    }

    // ================= RABIN KARP =================

    public static List<Integer> rabinKarp(String text, String pattern) {

        List<Integer> positions = new ArrayList<>();

        int n = text.length();
        int m = pattern.length();

        if (m > n) {
            return positions;
        }

        int base = 256;
        int mod = 1000000007;

        long patternHash = 0;
        long textHash = 0;
        long power = 1;

        for (int i = 0; i < m - 1; i++) {
            power = (power * base) % mod;
        }

        for (int i = 0; i < m; i++) {

            patternHash =
                    (patternHash * base + pattern.charAt(i)) % mod;

            textHash =
                    (textHash * base + text.charAt(i)) % mod;
        }

        for (int i = 0; i <= n - m; i++) {

            if (patternHash == textHash) {

                if (text.regionMatches(
                        true, i, pattern, 0, m)) {

                    positions.add(i);
                }
            }

            if (i < n - m) {

                textHash =
                        (base * (textHash -
                        text.charAt(i) * power)
                        + text.charAt(i + m)) % mod;

                if (textHash < 0) {
                    textHash += mod;
                }
            }
        }

        return positions;
    }

    // ================= AHO CORASICK =================

    public static List<String> ahoCorasick(
            String text,
            String[] keywords) {

        List<String> found = new ArrayList<>();

        text = text.toLowerCase();

        for (String keyword : keywords) {

            if (kmpSearch(text, keyword.toLowerCase()).size() > 0) {

                found.add(keyword);
            }
        }

        return found;
    }
}
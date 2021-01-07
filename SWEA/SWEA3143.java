import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            String src = st.nextToken();
            String pattern = st.nextToken();
            int[] pi = new int[pattern.length()];
            for (int j = 0, i = 1; i < pattern.length(); i++) {
                while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) { j = pi[i - 1]; }
                if (pattern.charAt(i) == pattern.charAt(j)) { pi[i] = ++j; }
            }
            int answer = 0;
            for (int i = 0, j = 0; i < src.length(); i++) {
                while (j > 0 && src.charAt(i) != pattern.charAt(j)) { j = pi[j - 1]; }
                if (src.charAt(i) == pattern.charAt(j)) {
                    if (j == pattern.length() - 1) {
                        ++answer;
                        j = 0;
                    } else {
                        ++j;
                    }
                }
            }
            answer = answer + src.length() - answer * pattern.length();
            sb.append("#" + tc + ' ' + answer).append('\n');
        }
        System.out.print(sb);
    }
}
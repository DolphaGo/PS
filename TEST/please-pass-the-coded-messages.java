import static java.lang.Math.max;

import java.util.Arrays;

public class Solution {
    public static int solution(int[] l) {
        int n = l.length;
        int max = 0;
        Arrays.sort(l);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1 << n; i++) {
            int sum = 0;
            sb.setLength(0);
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    sum += l[j];
                    sb.append(l[j]);
                }
            }
            if (sum != 0 && sum % 3 == 0) {
                max = max(max, Integer.parseInt(sb.reverse().toString()));
            }
        }
        return max;
    }
}
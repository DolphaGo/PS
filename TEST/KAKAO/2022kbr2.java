import java.util.Stack;
import java.util.StringTokenizer;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String s = convert(n, k);
        StringTokenizer st = new StringTokenizer(s, "0");
        while (st.hasMoreTokens()) {
            final long l = Long.parseLong(st.nextToken());
            if(isPrime(l)) answer++;
        }
        return answer;
    }

    private boolean isPrime(long n) {
        if (n == 0 || n == 1) {return false;}
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private String convert(int n, int k) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        while (n > 0) {
            final int res = n % k;
            stack.add(res);
            n /= k;
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}

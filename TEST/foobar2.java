import static java.lang.Math.log;

public class Solution {
    public static int solution(int total_lambs) {
        return getMax(total_lambs) - getMin(total_lambs);
    }

    static int getMin(int total) {
        return (int) (log(total + 1) / log(2));
    }

    static int getMax(int total) {
        int prev = 0;
        int cur = 1;
        int answer = 0;
        while (total > 0) {
            ++answer;
            int sum = prev + cur;
            total -= sum;
            prev = cur;
            cur = sum;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(143));
    }
}
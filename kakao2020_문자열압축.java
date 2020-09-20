import java.util.*;

public class Main {
    public static int solution(String s) {
        Queue<String> q = new LinkedList<>();

        int ap = s.length() / 2;
        int min = s.length();
        for (int l = ap; l >= 1; l--) {
            int i = 0;
            int len = 0;
            while (i < s.length()) {
                if (i + l <= s.length()) {
                    String sub = s.substring(i, i + l);
                    q.add(sub);
                    i += l;
                } else {
                    len += s.length() - i;
                    break;
                }
            }
            while (!q.isEmpty()) {
                String a = q.poll();
                int cnt = 1;
                while (q.size() > 0 && q.peek().equals(a)) {
                    q.poll();
                    cnt++;
                }
                if (cnt != 1) len += l + String.valueOf(cnt).length();
                else len += l;
            }
            min = Math.min(min, len);
        }
        return min;
    }

    public static void main(String[] args){
        String s="abcabcabcabcdededededede";
        System.out.println(solution(s));
    }
}
import java.util.*;

class Solution {
    public static String solution(String encrypted_text, String key, int rotation) {
        Deque<Character> dq = new ArrayDeque<>();
        for (int i = 0; i < encrypted_text.length(); i++) {
            dq.addLast(encrypted_text.charAt(i));
        }

        int len = encrypted_text.length();
        int ro = Math.abs(rotation) % len;

        if (rotation > 0) {//복원 : 왼쪽
            while (ro-- > 0) dq.addLast(dq.pollFirst());
        } else {//복원 : 오른쪽
            while (ro-- > 0) dq.addFirst(dq.pollLast());
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<key.length();i++){
            char c = dq.pollFirst();
            int k = key.charAt(i)-'a' + 1;
            while(k-->0){
                c--;
                if(c<'a') c='z';
            }
            sb.append(c);
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static void main(String[] args) {
        solution("bac","dbc",1);
    }
}
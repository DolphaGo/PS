import java.util.*;

class Solution {
    public int solution(String S) {
        Stack<Integer> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(S);
        while (st.hasMoreTokens()) {
            final String s = st.nextToken();
            switch (s) {
                case "+":
                    if (stack.isEmpty()) {return -1;}
                    final int t1 = stack.pop();
                    if (stack.isEmpty()) {return -1;}
                    final int t2 = stack.pop();
                    stack.push(t1 + t2);
                    break;
                case "-":
                    if (stack.isEmpty()) {return -1;}
                    final int tm1 = stack.pop();
                    if (stack.isEmpty()) {return -1;}
                    final int tm2 = stack.pop();
                    stack.push(tm1 - tm2);
                    break;
                case "DUP":
                    if (stack.isEmpty()) {return -1;}
                    stack.push(stack.peek());
                    break;
                case "POP":
                    if (stack.isEmpty()) {return -1;}
                    stack.pop();
                    break;
                default:
                    stack.push(Integer.parseInt(s));
                    break;
            }
        }
        if(stack.isEmpty()) return -1;
        return stack.pop();
    }
}

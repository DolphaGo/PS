import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long answer = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<n; i++) {
            Integer h = Integer.parseInt(br.readLine());
            while(!stack.isEmpty()&& stack.peek()<=h) {
                stack.pop();
            }
            answer += stack.size();
            stack.push(h);
        }
        System.out.println(answer);
    }
}
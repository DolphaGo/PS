import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s=br.readLine();
        
        //길이를 저장할 Stack
        Stack<Integer> len=new Stack<>();
        //괄호 연산에 사용할 Stack
        Stack<Character> stack=new Stack<>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='('){
                stack.push(c);
            }else if(c==')'){
                int l=0;
                while(stack.peek()!='('){
                    stack.pop();
                    l+=len.pop();
                }
                // '(' 제거
                stack.pop();
                // '(' 앞 숫자
                int k=stack.pop()-'0';
                len.pop(); // 길이가 아닌 반복자로 사용

                stack.push('0'); //내용물은 중요하지가 않음
                len.push(k*l); //길이 업데이트

            }else{
                stack.push(c);
                len.push(1);
            }
        }
        int answer=0;
        while(!len.isEmpty()) answer+=len.pop();
        System.out.println(answer);
    }
}
import java.util.*;
import java.io.*;

public class line01 {
	static String inputString="([뭐야}(>)ㅠㅠ()";
	public static void main(String[] args) throws IOException {
		System.out.println(solution(inputString));
	}

	static int solution(String inputString) {
		Stack<Integer> stack=new Stack<>();
		int cnt=0;
		boolean flag=true;
		for(int i=0;i<inputString.length();i++) {
			char c=inputString.charAt(i);
			if(c=='('||c=='['||c=='{'||c=='<') stack.add(1);
			else if(c==')'||c==']'||c=='}'||c=='>') {
				if(stack.size()==0) {
					flag=false;
					break;
				}else {
					stack.pop();
					++cnt;
				}
			}
		}
		
		if(flag) return cnt;
		else return -1;
	}
}
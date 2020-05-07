import java.util.*;
import java.io.*;

public class boj1874 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int n=Integer.parseInt(br.readLine());
		Stack<Integer> stack=new Stack<Integer>();
		int val=1;
		stack.push(val++);
		sb.append("+"+"\n");
		for(int i=1;i<=n;i++) {
			int num=Integer.parseInt(br.readLine());
			while(stack.size()==0||stack.peek()<num) {
				stack.push(val++);
				sb.append("+"+"\n");
			}
			if(stack.peek()==num) {
				stack.pop();
				sb.append("-"+"\n");
			}else {
				System.out.println("NO");
				return;
			}
		}
		System.out.print(sb);
	}
}
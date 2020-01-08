import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> q=new PriorityQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1,Integer o2) {
				return Integer.compare(o2, o1);
			}
		});
		String s=br.readLine();
		for(int i=0;i<s.length();i++) {
			int val=s.charAt(i)-'0';
			q.add(val);
		}
		while(!q.isEmpty()) System.out.print(q.poll());
	}
} 
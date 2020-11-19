import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> plus=new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o2,o1);
			}
		});

		PriorityQueue<Integer> minus=new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1,o2);
			}
		});

		int zero=0;
		for (int i = 0; i < n; i++) {
			int val= Integer.parseInt(br.readLine());
			if(val>0) plus.add(val);
			else if(val==0) ++zero;
			else minus.add(val);
		}
		int answer=getAnswer(plus)+getAnswer(minus,zero);
		System.out.println(answer);
	}
	static int getAnswer(PriorityQueue<Integer> q){
		int answer=0;
		while(q.size()>=2){
			int val1=q.poll();
			int val2=q.poll();
			if(val1*val2>val1+val2) answer+=val1*val2;
			else answer+=val1+val2;
		}
		while(!q.isEmpty()) answer+=q.poll();
		return answer;
	}
	static int getAnswer(PriorityQueue<Integer> q,int zero){
		int answer=0;
		while(q.size()>=2){
			int val1=q.poll();
			int val2=q.poll();
			answer+=val1*val2;
		}
		while(zero-->0) q.poll();
		while(!q.isEmpty()) answer+=q.poll();
		return answer;
	}
}

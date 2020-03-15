import java.util.*;
import java.io.*;

public class boj1091 {
	static final int MAX=150000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		
		int[] card=new int[n];
		int[] order=new int[n];
		for(int i=0;i<n;i++) {
			card[i]=i%3;
			order[i]=i;
		}
		
		int[] P=new int[n];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			P[i]=Integer.parseInt(st.nextToken());
		}
		HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			map.put(i,Integer.parseInt(st.nextToken()));
		}
		
		int cnt=0;
		while(true) {
			//검사
			boolean flag=true;
			for(int i=0;i<n;i++) {
				if(card[i]!=P[i]) {
					flag=false;
					break;
				}
			}
			if(cnt>MAX||flag) break;
			
			//섞기
			++cnt;
			int temp[]=card.clone();
			for(int i=0;i<n;i++) {
				int cur=order[i];
				order[i]=map.get(cur); //순서 갱신
				card[cur]=temp[order[i]]; //카드 갱신
			}
		}
		System.out.println(cnt>MAX?-1:cnt);
	}
}

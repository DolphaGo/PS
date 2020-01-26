import java.io.*;
import java.util.*;

public class boj2980 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken()); //신호등 개수
		int l=Integer.parseInt(st.nextToken());// 도로의 길이
		int info[][]=new int[n][3];
		int street[]=new int[l+1];
		boolean light[]=new boolean[l+1];
		Arrays.fill(light, true);
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			info[i][0]=Integer.parseInt(st.nextToken());//신호등 위치
			info[i][1]=Integer.parseInt(st.nextToken());//빨간색 지속
			info[i][2]=Integer.parseInt(st.nextToken());//초록색 지속
			street[info[i][0]]=info[i][1]+1;
			light[info[i][0]]=false;
		}
		Queue<Integer> q=new LinkedList<>();
		q.add(0);
		int t=0;
		while(!q.isEmpty()) {
			int cur=q.poll();
			if(cur==l) break;
			t++;
			//신호등 사이클
			for(int i=0;i<n;i++) {
				int loc=info[i][0];
				if(!light[loc]) {
					if(street[loc]==1) {
						street[loc]=info[i][2];
						light[loc]=true;
					}else --street[loc];
				}else {
					if(street[loc]==1) {
						street[loc]=info[i][1];
						light[loc]=false;
					}else --street[loc];
				}
				
			}
			//상근 이동
			if(light[cur]) q.add(cur+1);
			else q.add(cur);
		}
		System.out.println(t);
	}
}
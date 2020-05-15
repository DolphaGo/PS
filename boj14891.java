import java.util.*;
import java.io.*;

public class boj14891 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		char arr[][]=new char[4][8];
		for(int i=0;i<4;i++) arr[i]=br.readLine().toCharArray();
		
		int k=Integer.parseInt(br.readLine());
		Queue<int[]> q=new LinkedList<int[]>();
		for(int i=0;i<k;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken())-1;
			int dir=Integer.parseInt(st.nextToken());
			
			//왼쪽확인
			int j=a-1;
			int ddir=dir;
			while(j>=0) {
				//j가 왼쪽(2), j+1이 오른쪽(6)
				if(arr[j][2]!=arr[j+1][6]) {
					ddir*=-1;
					q.add(new int[] {j,ddir});
					--j;
				}else break;
			}
			j=a+1;
			ddir=dir;
			while(j<4) {
				//j-1이 왼쪽,j가 오른쪽
				if(arr[j-1][2]!=arr[j][6]) {
					ddir*=-1;
					q.add(new int[] {j,ddir});
					++j;
				}else break;
			}
			
			//회전
			q.add(new int[] {a,dir});
			while(!q.isEmpty()) {
				int[] p=q.poll();
				if(p[1]==1) {
					char tmp=arr[p[0]][7];
					for(int idx=6;idx>=0;--idx) {
						arr[p[0]][idx+1]=arr[p[0]][idx];
					}
					arr[p[0]][0]=tmp;
				}else {
					char tmp=arr[p[0]][0];
					for(int idx=1;idx<=7;idx++) {
						arr[p[0]][idx-1]=arr[p[0]][idx];
					}
					arr[p[0]][7]=tmp;
				}
			}
		}
		int answer=0;
		for(int i=0;i<4;i++) {
			if(arr[i][0]=='1') answer+=pow(i);
		}
		System.out.println(answer);
	}
	static int pow(int i) {
		return i==0?1:2*pow(i-1);
	}
}
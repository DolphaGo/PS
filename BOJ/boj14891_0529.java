import java.io.*;
import java.util.*;

public class boj14891_0529 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		char[][] arr=new char[4][8];
		for(int i=0;i<4;i++) {
			arr[i]=br.readLine().toCharArray();
		}
		
		Queue<int[]> q=new LinkedList<int[]>();
		int k=Integer.parseInt(br.readLine());
		for(int i=0;i<k;i++) {
			st=new StringTokenizer(br.readLine());
			int tar=Integer.parseInt(st.nextToken())-1;
			int dir=Integer.parseInt(st.nextToken());
			
			//자기 자신
			q.add(new int[] {tar,dir});
			//왼쪽
			int j=tar-1;
			int d=dir;
			while(j>=0&&arr[j][2]!=arr[j+1][6]) {
				d*=-1;
				q.add(new int[] {j--,d});
			}
			//오른쪽
			j=tar+1;
			d=dir;
			while(j<4&&arr[j-1][2]!=arr[j][6]) {
				d*=-1;
				q.add(new int[] {j++,d});
			}
			
			//회전 한번에 처리
			while(!q.isEmpty()) {
				int[] p=q.poll();
				rotate(arr[p[0]],p[1]);
			}
		}
		
		int answer=0;
		for(int i=0;i<4;i++) {
			if(arr[i][0]=='1') answer+=pow(2,i);
		}
		System.out.println(answer);
	}
	static int pow(int a,int b) {
		return b==0?1:a*pow(a,b-1);
	}
	static void rotate(char[] arr,int dir) {
		char temp;
		switch(dir) {
		case -1: //반시계
			temp=arr[0];
			for(int i=1;i<=7;i++) arr[i-1]=arr[i];
			arr[7]=temp;
			break;
		case 1: // 시계
			temp=arr[7];
			for(int i=6;i>=0;i--) arr[i+1]=arr[i];
			arr[0]=temp;
			break;
		}
	}
}
import java.io.*;
import java.util.*;

class swea4013 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int answer=0;
			int k=Integer.parseInt(br.readLine());
			int arr[][]=new int[4][8];
			for(int i=0;i<4;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<8;j++) {
					arr[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			for(int i=0;i<k;i++) {
				st=new StringTokenizer(br.readLine());
				int num=Integer.parseInt(st.nextToken())-1;
				int dir=Integer.parseInt(st.nextToken());
				rotate(arr,num,dir);
			}
			
			for(int i=0;i<4;i++) {
				if(arr[i][0]==1) answer+=pow(2,i);
			}
			sb.append("#" + tc + " " + answer + "\n");
		}
		System.out.print(sb);
	}
	static void rotate(int[][] arr,int num,int dir) {
		Queue<int[]> q=new LinkedList<int[]>();
		q.add(new int[] {num,dir});

		//왼쪽
		int j=num-1;
		int d=dir;
		while(j>=0&&arr[j][2]!=arr[j+1][6]) {
			d*=-1;
			q.add(new int[] {j--,d});
		}
		
		//오른쪽
		j=num+1;
		d=dir;
		while(j<4&&arr[j-1][2]!=arr[j][6]) {
			d*=-1;
			q.add(new int[] {j++,d});
		}
		while(!q.isEmpty()) {
			int[] p=q.poll();
			int x=p[0];
			int temp=0;
			switch(p[1]) {
			case 1://시계방향
				temp=arr[x][7];
				for(int i=7;i>=1;i--) arr[x][i]=arr[x][i-1];
				arr[x][0]=temp;
				break;
			case -1://반시계방향
				temp=arr[x][0];
				for(int i=0;i<=6;i++) arr[x][i]=arr[x][i+1];
				arr[x][7]=temp;
				break;
			}
		}
	}
	static int pow(int a,int b) {
		return b==0?1:a*pow(a,b-1);
	}
}
import java.io.*;
import java.util.*;

public class boj14889_0528 {
	static int n,answer;
	static int arr[][];
	static boolean team[];
	static int[] A,B;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//데이터 입력 받기
		n=Integer.parseInt(br.readLine());
		arr=new int[n][n];
		for(int y=0;y<n;y++) {
			st=new StringTokenizer(br.readLine());
			for(int x=0;x<n;x++) {
				arr[y][x]=Integer.parseInt(st.nextToken());
			}
		}
		
		//n/2 만큼 두 팀으로 나누고 차이값 계산하기.
		answer=Integer.MAX_VALUE;
		team=new boolean[n];
		A=new int[n/2];
		B=new int[n/2];
		go(0,0);
		System.out.println(answer);
	}
	static void go(int v,int select) {
		if(select==n/2) {
			for(int i=0,bidx=0;i<n;i++) {
				if(!team[i]) B[bidx++]=i;
			}
			int aval=0;
			int bval=0;
			for(int i=0;i<n/2;i++) {
				for(int j=0;j<n/2;j++) {
					aval+=arr[A[i]][A[j]];
					bval+=arr[B[i]][B[j]];
				}
			}
			int diff=Math.abs(aval-bval);
			answer=answer>diff?diff:answer;
			return;
		}
		if(v==n) return;
		
		team[v]=true;
		A[select]=v;
		go(v+1,select+1);
		team[v]=false;
		go(v+1,select);
	}
}
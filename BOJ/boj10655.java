import java.io.*;
import java.util.*;

public class boj10655 {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n=Integer.parseInt(br.readLine());
		int[][] arr=new int[n][2];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken());
			arr[i][1]=Integer.parseInt(st.nextToken());
		}
		int dist[]=new int[n-1];
		int sum=0;
		for(int i=0;i<n-1;i++) {
			dist[i]=manhatten(arr[i], arr[i+1]);
			sum+=dist[i];
		}
		int max=0;
		//시작(0)과 마지막(n-1)은 반드시 거쳐야 한다.
		for(int i=0;i<n-2;i++) {
			//i에서 i+1을 skip하고 i+2로 갈 때의 거리
			int skip=manhatten(arr[i],arr[i+2]);
			//최대 이득을 내려면 A=(dist[i]+dist[i+1]):i에서 i+1, i+1에서 i+2로 가는 비용
			//A-skip이 클 수록, 반대로는 전체 거리인 sum-A+skip이 작을 수록 좋다.
			max=Math.max(max,dist[i]+dist[i+1]-skip);
		}
		System.out.println(sum-max);
	}
	static int manhatten(int[] from,int[] to) {
		return Math.abs(from[0]-to[0])+Math.abs(from[1]-to[1]);
	}
}

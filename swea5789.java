import java.io.*;
import java.util.*;

class swea5789 {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int Q=Integer.parseInt(st.nextToken());
			int[] arr=new int[N];
			for(int i=1;i<=Q;i++) {
				st=new StringTokenizer(br.readLine());
				int L=Integer.parseInt(st.nextToken())-1;
				int R=Integer.parseInt(st.nextToken())-1;
				for(int j=L;j<=R;j++) arr[j]=i;
			}
			sb.append("#"+tc+" ");
			for(int i=0;i<N;i++) sb.append(arr[i]+" ");
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
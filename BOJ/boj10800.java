import java.util.*;
import java.io.*;

public class boj10800 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//자신보다 크기가 작고 색이 다른 공을 잡아 그 크기만큼 점수를 얻기
		//다른 공을 사로잡은 이후에도 본인 공의 색과 크기는 변하지 않음
		int n=Integer.parseInt(br.readLine());
		int ball[][]=new int[n][3];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			ball[i][0]=i;//원래 인덱스
			ball[i][1]=Integer.parseInt(st.nextToken());//색(1~N)
			ball[i][2]=Integer.parseInt(st.nextToken());//크기
		}
		//크기 순 정렬
		Arrays.sort(ball,new Comparator<int[]>() {
			public int compare(int[] o1,int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		int ans[]=new int[n];

		int sum=0;
		int color[]=new int[n+1];
		Queue<Integer> q=new LinkedList<>();
		for(int i=0;i<n;i++){
			int idx=ball[i][0];
			int c=ball[i][1];
			int s=ball[i][2];
			ans[idx]=sum-color[c];
			q.add(i);
			while(i+1<n&&s==ball[i+1][2]) {
				++i;
				ans[ball[i][0]]=sum-color[ball[i][1]];
				q.add(i);
			}
			
			while(!q.isEmpty()) {
				int p=q.poll();
				sum+=ball[p][2];
				color[ball[p][1]]+=ball[p][2];
			}
		}

		StringBuilder sb=new StringBuilder();
		for(int i=0;i<n;i++) {
			sb.append(ans[i]+"\n");
		}
		System.out.println(sb);
	}
}
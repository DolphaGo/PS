import java.io.*;
import java.util.*;

public class boj1043 {
	static int n,m;
	static int[] p;
	static boolean[] known;
	static int getParent(int a) {
		if(p[a]==a) return a;
		return p[a]=getParent(p[a]);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		p=new int[n+1];
		for(int i=1;i<=n;i++) p[i]=i;
		known=new boolean[n+1];
		st=new StringTokenizer(br.readLine());
		int known_num=Integer.parseInt(st.nextToken());
		for(int i=0;i<known_num;i++) {
			known[Integer.parseInt(st.nextToken())]=true;
		}//진실을 아는 사람들
		//=>이 사람들과 같은 파티에 있는 사람들에게도 모두 진실로 말해야함.
		int answer=0;
		int[][] temp=new int[m][];
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int num=Integer.parseInt(st.nextToken());
			temp[i]=new int[num];
			boolean flag=false;
			int first=Integer.parseInt(st.nextToken());
			temp[i][0]=first;
			int min=getParent(first);
			if(known[min]) flag=true;
			for(int j=1;j<num;j++) {
				int second=Integer.parseInt(st.nextToken());
				temp[i][j]=second;
				if(min>getParent(second)) min=getParent(second);
				first=getParent(first);
				second=getParent(second);
				if(first<second) p[second]=first;
				else p[first]=second;
				if(known[second]) flag=true;
				first=second;
			}
			if(flag) known[min]=true; 
		}
		for(int i=0;i<m;i++) {
			boolean flag=true;
			for(int j=0;j<temp[i].length;j++) {
				if(known[getParent(temp[i][j])]) {
					flag=false;
					break;
				}
			}
			if(flag) ++answer;
		}
		
		System.out.println(answer);
	}
}

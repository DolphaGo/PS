import java.util.*;
import java.io.*;
public class Main {
	static int getParent(int[] p,int a) {
		if(p[a]==a) return a;
		return p[a]=getParent(p,p[a]);
	}
	static int answ  er,n,m,p[],digit[];
	static ArrayList<Integer> list=new ArrayList<Integer>();
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		p=new int[n+1];
		for(int i=1;i<=n;i++) p[i]=i;
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			a=getParent(p,a);
			b=getParent(p,b);
			if(a<b) p[b]=a;
			else p[a]=b;
		}
		digit=new int[n+1];
		for(int i=1;i<=n;i++) {
			digit[p[i]]++;
			if(digit[p[i]]==1) list.add(p[i]);
		}
		get3(0,0,1);
		System.out.println(Arrays.toString(p));
		System.out.println(answer);
	}
	static void get3(int v,int cnt,int val) {
		if(cnt==3) {
			answer+=val;
			return;
		}
		if(v==list.size()) return;
		get3(v+1,cnt+1,val*digit[list.get(v)]);
		get3(v+1,cnt,val);
	}
}

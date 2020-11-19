import java.util.*;
import java.io.*;

public class Main {
	static int find(int[] p,int a) {
		if(p[a]==a) return a;
		return p[a]=find(p,p[a]);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			int n=Integer.parseInt(br.readLine());
			HashMap<String, Integer> map=new HashMap<String, Integer>();
			int p[]=new int[2*n+1];
			int num[]=new int[2*n+1];
			for(int i=1;i<=2*n;i++) {
				p[i]=i;
				num[i]=1;
			}
			
			int idx=0;
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				String name1=st.nextToken();
				String name2=st.nextToken();
				if(map.get(name1)==null) map.put(name1,++idx);
				if(map.get(name2)==null) map.put(name2,++idx);
				
				int x=map.get(name1);
				int y=map.get(name2);
				
				x=find(p,x);
				y=find(p,y);
				if(x!=y) {
					if(x<y) {
						num[x]+=num[y];
						p[y]=x;
						sb.append(num[x]+"\n");
					}else {
						num[y]+=num[x];
						p[x]=y;
						sb.append(num[y]+"\n");
					}
				}else sb.append(num[x]+"\n");
			}
		}
		System.out.print(sb);
	}
}

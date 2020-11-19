import java.util.*;
import java.io.*;

public class boj1038 {
	static StringBuilder sb=new StringBuilder();
	static ArrayList<Long> list=new ArrayList<Long>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		for(int i=1;i<=10;i++) {
			nCr(0,i);
			if(list.size()>n) break;
		}
		System.out.println(list.size()<=n?-1:list.get(n));
	}
	static boolean visit[]=new boolean[10];
	static void nCr(int v,int lim) {
		if(v==lim) {
			list.add(Long.parseLong(sb.toString()));
			return;
		}
		for(int i=0;i<=9;i++) {
			if(visit[i]) continue;
			if(v==0) {
				visit[i]=true;
				sb.append(i);
				nCr(v+1,lim);
				visit[i]=false;
				sb.deleteCharAt(v);
			}else {
				int prev=sb.charAt(v-1)-'0';
				for(int j=0;j<prev;j++) {
					if(visit[j]) continue;
					visit[j]=true;
					sb.append(j);
					nCr(v+1,lim);
					visit[j]=false;
					sb.deleteCharAt(v);
				}
				return;
			}
		}
	}
}
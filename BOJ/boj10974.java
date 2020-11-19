import java.io.*;
import java.util.*;

public class boj10974 {
	static int[] select;
	static boolean[] v;
	static int n;
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		v=new boolean[n];
		select=new int[n];
		dfs(0);
		System.out.println(sb.toString());
	}
	static void dfs(int l) {
		if(l==n) {
			for(int i=0;i<n;i++) sb.append(select[i]+" ");
			sb.append("\n");
			return;
		}
		for(int i=0;i<n;i++) {
			if(v[i]) continue;
			v[i]=true;
			select[l]=i+1;
			dfs(l+1);
			v[i]=false;
		}
	}
}
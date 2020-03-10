import java.util.*;
import java.io.*;

public class boj10775 {
	static int p[];
	static boolean docked[];
	static int dock(int v,int a) {
		if(a==0) return 0;
		if(!docked[a]) return a;
		else return p[v]=dock(v,p[a-1]);
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G=Integer.parseInt(br.readLine());
		int P=Integer.parseInt(br.readLine());
		p=new int[G+1];
		docked=new boolean[G+1];
		for(int i=1;i<=G;i++) p[i]=i;
		
		int answer=0;
		for(int i=0;i<P;i++) {
			int g=Integer.parseInt(br.readLine());
			int res=dock(g,p[g]);
			if(res!=0) {
				++answer;
				docked[res]=true;
			}else break;
		}
		System.out.println(answer);
	}
}

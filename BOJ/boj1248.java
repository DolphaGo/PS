import java.io.*;
import java.util.*;

public class boj1248 {
	static char[][] data;
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		data=new char[n][n];
		
		//이 문제의 핵심 : 1차원 데이터를 2차원으로 만드는 생각.
		String s=br.readLine();
		int idx=0;
		for(int i=0;i<n;i++) {
			for(int j=i;j<n;j++) {
				data[i][j]=s.charAt(idx++);
			}
		}
		val=new int[n];
		sum=new int[n][n];
		go(0);
	}
	static int val[];
	static int sum[][];
	static void go(int v) {
		if(v==n) {
			for(int i=0;i<n;i++) {
				System.out.print(val[i]+" ");
			}
			System.exit(0);
		}
		
		for(int i=0;i<=20;i++) {
			val[v]=i-10;
			
			for(int j=0;j<=v;j++) {
				for(int k=v;k<n;k++) {
					sum[j][k]+=val[v];
				}
			}
			if(check(v)) go(v+1);
			for(int j=0;j<=v;j++) {
				for(int k=v;k<n;k++) {
					sum[j][k]-=val[v];
				}
			}
			val[v]=0;
		}
	}
	static boolean check(int v) {
		for(int i=0;i<=v;i++) if(!Ok(i,v)) return false;
		return true;
	}
	static boolean Ok(int i,int j) {
		int value=sum[i][j];
		char c=data[i][j];
		if(value>0&&c=='+') return true;
		else if(value==0&&c=='0') return true;
		else if(value<0&&c=='-') return true;
		else return false;
	}
}
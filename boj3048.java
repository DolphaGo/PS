import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n1=Integer.parseInt(st.nextToken());
		int n2=Integer.parseInt(st.nextToken());
		String s=br.readLine();
		char a1[]=new char[n1];
		for(int i=s.length()-1;i>=0;i--) a1[s.length()-1-i]=s.charAt(i);
		char a2[]=br.readLine().toCharArray();
		char ants[]=new char[n1+n2];
		int dir[]=new int[n1+n2];
		for(int i=0;i<n1;i++) {
			ants[i]=a1[i];
			dir[i]=1;//왼쪽에서 오른쪽
		}
		for(int i=n1;i<n1+n2;i++) {
			ants[i]=a2[i-n1];
			dir[i]=2; //오른쪽에서 왼쪽
		}
		int T=Integer.parseInt(br.readLine());
		while(T>0) {
			T--;
			for(int i=0;i<n1+n2-1;i++) {
				if(dir[i]==1&&dir[i+1]==2) {
					char temp=ants[i];
					ants[i]=ants[i+1];
					ants[i+1]=temp;
					int tdir=dir[i];
					dir[i]=dir[i+1];
					dir[i+1]=tdir;
					i++;
				}
			}
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<n1+n2;i++) sb.append(ants[i]);
		System.out.println(sb.toString());
	}
}
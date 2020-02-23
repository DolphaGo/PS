import java.io.*;
public class boj11729 {
	static StringBuilder sb=new StringBuilder();
	static int cnt=0;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		towerOfHanoi('1','3','2',n);
		
		System.out.println(cnt);
		System.out.println(sb.toString());
	}
	static void towerOfHanoi(char s,char d,char temp,int n) {
		if(n<=0) return;
		towerOfHanoi(s, temp, d,n-1);
		++cnt;
		sb.append(s+" "+d+"\n");
		towerOfHanoi(temp,d, s, n-1);
	}
}

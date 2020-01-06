import java.util.*;
import java.io.*;

public class boj1267 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n=Integer.parseInt(br.readLine());
		st=new StringTokenizer(br.readLine());
		int Y=0;
		int M=0;
		for(int i=0;i<n;i++) {
			int val=Integer.parseInt(st.nextToken());
			Y+=(val/30+1)*10;
			M+=(val/60+1)*15;
		}
		if(Y>M) System.out.println("M "+M);
		else if(M>Y) System.out.println("Y "+Y);
		else System.out.println("Y M "+M);
	}
}

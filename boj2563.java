import java.util.*;
import java.io.*;

public class boj2563 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		StringTokenizer st;
		boolean map[][]=new boolean[100][100];
		int answer=0;
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			for(int yy=y;yy<y+10;yy++) {
				for(int xx=x;xx<x+10;xx++) {
					if(!map[yy][xx]) {
						map[yy][xx]=true;
						++answer;
					}
				}
			}
		}
		System.out.println(answer);
		
		
	}
}

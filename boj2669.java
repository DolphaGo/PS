import java.io.*;
import java.util.*;

public class boj2669 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		boolean map[][]=new boolean[101][101];
		int answer=0;
		for(int i=0;i<4;i++) {
			st=new StringTokenizer(br.readLine());
			int sx=Integer.parseInt(st.nextToken());
			int sy=Integer.parseInt(st.nextToken());
			int ex=Integer.parseInt(st.nextToken());
			int ey=Integer.parseInt(st.nextToken());
			for(int y=sy;y<ey;y++) {
				for(int x=sx;x<ex;x++) {
					if(!map[y][x]) {
						map[y][x]=true;
						answer++;
					}
				}
			}
		}
		System.out.println(answer);
	}
}

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int r1 = Integer.parseInt(st.nextToken());

			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			
			double r=getdis(y1,x1,y2,x2);
			
			int answer=0;
			if(r>r1+r2) {
				answer=0;
			}else if(r==r1+r2) {
				answer=1;
			}else {
				//원이 일치
				if(r==0&&r1==r2) answer=-1;
				//내접
				else if(r2>r1&&r1+r==r2) answer=1;
				else if(r1>r2&&r2+r==r1) answer=1;
				//포함
				else if(r1>r2&&r1>r+r2) answer=0;
				else if(r2>r1&&r2>r+r1) answer=0;
				else answer=2;
			}
			System.out.println(answer);
		}
	}
	static double getdis(int y1,int x1,int y2,int x2) {
		return Math.sqrt(Math.pow(y1-y2, 2)+Math.pow(x1-x2,2));
	}
}

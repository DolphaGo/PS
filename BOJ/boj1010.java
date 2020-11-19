import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			double answer=1.0;
			int iter=n;
			for(int i=0;i<iter;i++) answer*=1.0*(m--)/(n--);
			System.out.println(Math.round(answer));
		}
	}
}
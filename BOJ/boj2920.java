import java.util.*;
import java.io.*;

public class boj2920 {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int state=0;
		int base=Integer.parseInt(st.nextToken());
		for(int i=1;i<8;i++) {
			int val=Integer.parseInt(st.nextToken());
			if(base<val) ++state;
			else --state;
			base=val;
		}
		if(state==7) System.out.println("ascending");
		else if(state==-7) System.out.println("descending");
		else System.out.println("mixed");
	}
}

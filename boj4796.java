import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int tc=0;
		while(true) {
			++tc;
			int answer=0;
			st=new StringTokenizer(br.readLine());
			int l=Integer.parseInt(st.nextToken());
			int p=Integer.parseInt(st.nextToken());
			int v=Integer.parseInt(st.nextToken());
			if(l==0&&p==0&&v==0) break;
			//v를 p개 씩 토막내고 토막내진 것에서 l보다 큰지 작은지 분기
			while(v>0) {
				int token=0;
				if(v>=p) {
					token=p;
					v-=p;
				}else {
					token=v;
					v=0;
				}
				if(token>=l) answer+=l;
				else answer+=token;
			}
			sb.append("Case "+tc+": "+answer).append("\n");
		}
		System.out.print(sb);
	}
}
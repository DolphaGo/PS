import java.util.*;
import java.io.*;

public class boj1244 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		boolean sw[]=new boolean[n+1];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) {
			int val=Integer.parseInt(st.nextToken());
			if(val==1) sw[i]=true;
		}
		int m=Integer.parseInt(br.readLine());
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());
			int num=Integer.parseInt(st.nextToken());
			if(s==1) {
				for(int j=num;j<=n;j+=num) {
					sw[j]=!sw[j];
				}
			}else {
				sw[num]=!sw[num];
				int len=1;
				while(num-len>=1&&num+len<=n&&sw[num-len]==sw[num+len]) {
					sw[num-len]=!sw[num-len];
					sw[num+len]=!sw[num+len];
					++len;
				}
			}
		}
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<=n;i++) {
			if(sw[i]) sb.append(1+" ");
			else sb.append(0+" ");
			if(i%20==0) sb.append("\n");
		}
		System.out.println(sb);
	}
}

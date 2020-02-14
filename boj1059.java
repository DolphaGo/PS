import java.io.*;
import java.util.*;

public class boj1059 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int digit[]=new int[L];
		for (int i = 0; i < L; i++) digit[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(digit);
		int n = Integer.parseInt(br.readLine());
		int left=0;
		int right=0;
		boolean flag=true;
		for(int i=0;i<L;i++) {
			if(n>digit[i]) left=digit[i];
			else if(n<digit[i]){
				right=digit[i];
				break;
			}else {
				flag=false;
				break;
			}
		}
		if(flag) System.out.println((right-n)*(n-left)-1);
		else System.out.println(0);
	}
}
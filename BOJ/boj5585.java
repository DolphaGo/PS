import java.util.*;
import java.io.*;
public class boj5585{
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		n=1000-n;
		int[] coin= {500,100,50,10,5,1};
		int answer=0;
		for(int i=0;i<coin.length;i++) {
			if(n>=coin[i]) {
				answer+=n/coin[i];
				n%=coin[i];
			}
		}
		System.out.println(answer);
	}
}
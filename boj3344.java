import java.util.*;
import java.io.*;

public class boj3344 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		
		int rem=n%6;
		ArrayList<Integer> oddlist=new ArrayList<Integer>();
		ArrayList<Integer> evenlist=new ArrayList<Integer>();
		for(int i=1;i<=n;i+=2) oddlist.add(i);
		for(int i=2;i<=n;i+=2) evenlist.add(i);
		if(rem!=2&&rem!=3) {
		}else if(rem==2) {
			oddlist.set(0, 3);
			oddlist.set(1, 1);
			oddlist.remove(2);
			oddlist.add(5);
		}else {
			evenlist.remove(0);
			evenlist.add(2);
			oddlist.add(1);
			oddlist.add(3);
			oddlist.remove(0);
			oddlist.remove(0);
		}
		StringBuilder sb=new StringBuilder();
		for(int val:evenlist) sb.append(val+"\n");
		for(int val:oddlist) sb.append(val+"\n");
		System.out.println(sb);
	}
}

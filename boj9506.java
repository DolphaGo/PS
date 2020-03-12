import java.util.*;
import java.io.*;

public class boj9506 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> list=new ArrayList<Integer>();
		StringBuilder sb=new StringBuilder();
		while(true) {
			int num=Integer.parseInt(br.readLine());
			if(num==-1) break;
			int val=0;
			for(int i=1;i<num;i++) {
				if(num%i==0) {
					val+=i;
					list.add(i);
				}
			}
			if(val==num) {
				sb.append(num+" = "+list.get(0));
				for(int j=1;j<list.size();j++) sb.append(" + "+list.get(j));
				sb.append("\n");
			}else {
				sb.append(num+" is NOT perfect."+"\n");
			}
			list.clear();
		}
		System.out.println(sb);
	}
}

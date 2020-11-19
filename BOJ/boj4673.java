import java.io.*;
import java.util.*;

public class boj4673 {
	public static void main(String[] args) throws Exception {
		boolean check[]=new boolean[10001];
		for(int i=1;i<=10000;i++) {
			int val=i;
			int sum=i;
			while(val>0) {
				sum+=val%10;
				val/=10;
			}
			if(sum<=10000) check[sum]=true;
		}
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<=10000;i++) {
			if(!check[i]) sb.append(i+"\n");
		}
		System.out.println(sb);
	}
}
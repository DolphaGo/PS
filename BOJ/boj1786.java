import java.io.*;
import java.util.*;

public class boj1786 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] parent=br.readLine().toCharArray();
		char[] pattern=br.readLine().toCharArray();
		int[] pi=new int[pattern.length];
		
		int j=0;
		for(int i=1;i<pattern.length;i++) {
			while(j>0&&pattern[i]!=pattern[j]) j=pi[j-1];
			if(pattern[i]==pattern[j]) pi[i]=++j;
		}
		
		ArrayList<Integer> list=new ArrayList<Integer>();
		
		j=0;
		for(int i=0;i<parent.length;i++) {
			while(j>0&&parent[i]!=pattern[j]) j=pi[j-1];
			if(parent[i]==pattern[j]) {
				if(j==pattern.length-1) {
					list.add(i-pattern.length+1);
					j=pi[j]; //겹쳐서 매칭을 해도 됨
				}else {
					++j;
				}
			}
		}
		
		System.out.println(list.size());
		for(int i:list) System.out.print((i+1)+" ");
		
	}
}
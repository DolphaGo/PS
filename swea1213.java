import java.io.*;
import java.util.*;

class swea1213 {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		for(int t=0;t<10;t++) {
			int tc=Integer.parseInt(br.readLine());
			int answer=0;
			char[] pattern=br.readLine().toCharArray();
			char[] str=br.readLine().toCharArray();
			int[] pi=new int[pattern.length];
			
			//실패함수 만들기
			int j=0;
			for(int i=1;i<pattern.length;i++) {
				while(j>0&&pattern[i]!=pattern[j]) j=pi[j-1];
				if(pattern[j]==pattern[i]) pi[j]=++j;
			}
			
			//정답 검사
			j=0;
			for(int i=0;i<str.length;i++) {
				while(j>0&&str[i]!=pattern[j]) j=pi[j-1];
				if(pattern[j]==str[i]) {
					if(j==pattern.length-1) {
						++answer;
						j=pi[j];
					}else ++j;
				}
			}
			sb.append("#"+tc+" "+answer+"\n");
		}
		System.out.print(sb);
	}
}
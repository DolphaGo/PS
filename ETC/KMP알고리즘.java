import java.io.*;
import java.util.*;

public class KMP알고리즘 {
	static int[] pi;
	static char[] src;
	static char[] key;
	static boolean flag;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		String s=br.readLine();
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)>='0'&&s.charAt(i)<='9') continue;
			sb.append(s.charAt(i));
		}
		src=sb.toString().toCharArray();
		key=br.readLine().toCharArray();
		flag=false;
		getpi();
		kmp();
		System.out.println(flag?1:0);
	}
	
	static void getpi(){
	    pi=new int[key.length];
	    int j = 0;
	    for (int i = 1; i < key.length; i++){
	        while (j > 0 && key[i] != key[j]) j = pi[j - 1];//불일치가 일어날 경우
	        if (key[i] == key[j]) pi[i] = ++j;//prefix가 같은 가중치만큼 pi를 정해줍니다.
	    }
	}
	
	static void kmp(){
	    int j = 0;
	    for (int i = 0; i < src.length; i++){
	        while (j > 0 && src[i] != key[j])//현재 j만큼 찾았는데 이번에 비교하는 문자가 다른 경우
	            j = pi[j - 1];//문자열 B를 failure function 이후 부터 비교를 해줍니다.
	        if (src[i] == key[j]){//비교하는 문자가 같은 경우
	            if (j == key.length - 1){//문자열 B를 전부 탐색하였기 때문에 답에 위치를 넣어줍니다.
	                flag=true;
	            	j = pi[j];    //다음 탐색을 위하여 이번에도 failure function 이후 부터 비교를 해줍니다.
	            }else j++;//문자열 B의 다음 단어를 비교해줍니다.
	        }
	    }
	}


	
}
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s=br.readLine().toUpperCase();
        int[] count=new int[26];
        for(int i=0;i<s.length();i++){
            int idx=s.charAt(i)-'A';
            count[idx]++;
        }

        int max=0;
        for(int i=0;i<26;i++){
            max=Math.max(max,count[i]);
        }

        int cnt=0;
        char answer='\0';
        for(int i=0;i<26;i++){
            if(count[i]==max){
                ++cnt;
                answer=(char)('A'+i);
            }
        }

        System.out.println(cnt==1?answer:'?');

    }
}
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] count=new int[26];
        String s;
        while((s=br.readLine())!=null){
            for(int i=0;i<s.length();i++){
                char c=s.charAt(i);
                if('a'<=c && c<='z') {
                    int idx = c - 'a';
                    count[idx]++;
                }
            }
        }
        int max=0;
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<26;i++) max=Math.max(count[i],max);
        for(int i=0;i<26;i++) if(max==count[i]) sb.append((char)('a'+i));
        System.out.println(sb);
    }
}
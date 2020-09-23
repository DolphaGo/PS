import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int[] count=new int[26];
        for(int i=0;i<n;i++){
            int idx=br.readLine().charAt(0)-'a';
            count[idx]++;
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<26;i++){
            if(count[i]>=5) sb.append((char)('a'+i));
        }
        if(sb.length()==0) System.out.println("PREDAJA");
        else System.out.println(sb.toString());
    }
}
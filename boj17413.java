import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s=br.readLine();
        boolean[] visit=new boolean[s.length()];
        for(int i=0;i<s.length();){
            if(s.charAt(i)=='<'){
                while(s.charAt(i)!='>') visit[i++]=true;
                visit[i++]=true;
            }else if(s.charAt(i)==' '){
                visit[i++]=true;
            }else i++;
        }
        StringBuilder sb=new StringBuilder();
        int i=0;
        while(i<s.length()){
            if(visit[i]){
                while(i<s.length()&&visit[i]) sb.append(s.charAt(i++));
            }else{
                int j=i;
                while(j<s.length()&&!visit[j]) j++;
                for(int k=j-1;k>=i;k--) sb.append(s.charAt(k));
                i=j;
            }
        }
        System.out.print(sb);
    }
}
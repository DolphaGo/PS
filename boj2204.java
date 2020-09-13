import java.util.*;
import java.io.*;

public class Main {
    static class Info{
        String s;
        int idx;
        Info(String s,int idx){
            this.s=s;
            this.idx=idx;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Info> q=new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o1.s.compareTo(o2.s);
            }
        });
        StringBuilder sb=new StringBuilder();
        while(true){
            int n=Integer.parseInt(br.readLine());
            if(n==0) break;

            String[] s=new String[n];

            for(int i=0;i<n;i++){
                s[i]=br.readLine();
                String ls=s[i].toLowerCase();
                q.add(new Info(ls,i));
            }

            int idx=q.poll().idx;
            sb.append(s[idx]).append("\n");
            q.clear();
        }
        System.out.print(sb);
    }
}
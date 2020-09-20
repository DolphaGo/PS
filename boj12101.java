import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());

        List<String>[] list=new ArrayList[n+1];
        for(int i=0;i<=n;i++) list[i]=new ArrayList<>();

        for(int i=1;i<=n;i++) {
            for (int j = 1; j <= 3; j++) {
                if (i >= j){
                    if(i==j) list[i].add(String.valueOf(j));
                    else for(String s:list[i-j]) list[i].add(s+"+"+j);
                }
            }
        }
        Collections.sort(list[n]);
        if(list[n].size()<k) System.out.println(-1);
        else System.out.println(list[n].get(k-1));
    }
}
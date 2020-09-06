import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        int m=Integer.parseInt(br.readLine());
        boolean[] check=new boolean[n];
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken())-1;
            int y=Integer.parseInt(st.nextToken())-1;
            for(int j=x;j<y;j++){
                check[j]=true;
            }
        }
        int cnt=0;
        for(int i=0;i<n;i++){
            if(!check[i]) ++cnt;
        }
        System.out.println(cnt);

    }
}
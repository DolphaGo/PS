import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
        char[] arr=br.readLine().toCharArray();
        boolean[] visit=new boolean[n];
        int count=0;
        for(int i=0;i<n;i++){
            if(arr[i]=='H'){
                for(int j=i-k;j<=i+k;j++){
                    if(j>=0&&j<n && arr[j]=='P' && !visit[j]){
                        visit[j]=true;
                        ++count;
                        break;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
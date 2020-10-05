import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for(int tc=1;tc<=T;tc++){
            int n=Integer.parseInt(br.readLine());
            int[] arr=new int[n];
            st=new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());

            Arrays.sort(arr);

            int m=Integer.parseInt(br.readLine());
            st=new StringTokenizer(br.readLine());
            for(int i=0;i<m;i++){
                int val=Integer.parseInt(st.nextToken());
                int s=0;
                int e=n;
                while(s<e){
                    int mid=(s+e)>>1;
                    if(arr[mid]<val) s=mid+1;
                    else e=mid;
                }
                if(e<n&&arr[e]==val) sb.append(1);
                else sb.append(0);
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}
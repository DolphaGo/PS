import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        int[] arr=new int[n];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());

        int[] index=new int[n];
        for(int i=0;i<n;i++) index[i]=i;
        int[] lis=new int[n];
        int idx=0;
        lis[idx++]=arr[0];
        for(int i=1;i<n;i++){
            if(lis[idx-1]<arr[i]) {
                index[i]=idx;
                lis[idx++]=arr[i];
            }
            else{
                int s=0;
                int e=idx-1;
                while(s<e){
                    int m=(s+e)>>1;
                    if(lis[m]<arr[i]) s=m+1;
                    else e=m;
                }
                index[i]=e;
                lis[e]=arr[i];
            }
        }
        int t=idx-1;
        Stack<Integer> s=new Stack<>();
        for(int i=n-1;i>=0;i--){
            if(index[i]==t){
                s.push(arr[i]);
                t--;
            }
        }
        System.out.println(idx);
        StringBuilder sb=new StringBuilder();
        while(!s.isEmpty()) sb.append(s.pop()+" ");
        System.out.print(sb);
    }
}
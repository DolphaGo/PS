import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        int[] arr=new int[n];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());

        int answer=0;
        for(int i=0;i<n;i++){
              int left=0,right=0;
            double min=1e9+1;
            for(int j=i-1;j>=0;j--){
                double res=1.0*(arr[i]-arr[j])/(i-j);
                if(min>res){
                    ++left;
                    min=res;
                }
            }
            double max=-1e9-1;
            for(int j=i+1;j<n;j++){
                double res=1.0*(arr[j]-arr[i])/(j-i);
                if(max<res){
                    max=res;
                    ++right;
                }
            }
           answer=Math.max(answer,left+right);
        }
        System.out.println(answer);
    }
}
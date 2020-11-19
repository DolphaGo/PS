import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());

        int idx=0;
        int[] arr=new int[n];
        arr[idx++]=Integer.parseInt(br.readLine());

        for(int i=1;i<n;i++){
            int num=Integer.parseInt(br.readLine());
            int lastVal=arr[idx-1];
            if(lastVal<num) arr[idx++]=num;
            else lowerbound(arr,idx,num);
        }
        System.out.println(n-idx);
    }
    static void lowerbound(int[] arr,int idx,int num){
        int s=0;
        int e=idx-1;
        while(s<e){
            int m=(s+e)>>1;
            if(arr[m]<num) s=m+1;
            else e=m;
        }
        arr[e]=num;
    }
}
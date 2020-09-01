import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        int[] arr=new int[n];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());

        int[] max=new int[n];
        int[] min=new int[n];

        for(int i=0;i<n;i++){
            max[i]=1;
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j]&&max[i]<max[j]+1){
                    max[i]=max[j]+1;
                }
            }
        }

        for(int i=n-1;i>=0;i--){
            min[i]=1;
            for(int j=n-1;j>i;j--){
                if(arr[i]>arr[j]&&min[i]<min[j]+1){
                    min[i]=min[j]+1;
                }
            }
        }

        int answer=0;
        for(int i=0;i<n;i++){
            answer=Math.max(answer,max[i]+min[i]-1);
        }
        System.out.println(answer);
    }
}
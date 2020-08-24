import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int c=Integer.parseInt(st.nextToken());

        int[] arr=new int[n];
        int max=0;
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(br.readLine());
            max=Math.max(max,arr[i]);
        }
        Arrays.sort(arr);

        int answer=0;
        int s=1;
        int e=max;
        //c개를 배치해야함. N은 20만
        while(s<=e){
           int m=(s+e)>>1;
           if(test(arr,m,c)){
               answer=Math.max(m,answer);
               s=m+1;
           }else e=m-1;
        }
        System.out.println(answer);
    }
    static boolean test(int[] arr,int m,int c){
        int cnt=1;
        int l=arr[0];
        for(int i=1;i<arr.length;i++){
            if(arr[i]-l>=m){
                l=arr[i];
                ++cnt;
            }
        }
        if(cnt>=c) return true;
        else return false;
    }
}
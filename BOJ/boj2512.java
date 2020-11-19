import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n=Integer.parseInt(br.readLine());

        int[] arr=new int[n];
        StringTokenizer st=new StringTokenizer(br.readLine());
        int sum=0;
        int max=0;
        for(int i=0;i<n;i++) {
            arr[i]=Integer.parseInt(st.nextToken());
            sum+=arr[i];
            max=Math.max(max,arr[i]);
        }

        long m=Long.parseLong(br.readLine());

        if(sum<=m) System.out.println(max);
        else {
            long s = 1;
            long e = m;
            long answer = 0;
            while (s <= e) {
                long mid = (s + e) >> 1;
                long res = test(arr, mid);
                if (res <= m) {
                    answer = Math.max(answer, mid);
                    s = mid + 1;
                } else e = mid - 1;
            }
            System.out.println(answer);
        }
    }
    static long test(int[] arr,long mid){
        long res=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]>mid) res+=mid;
            else res+=arr[i];
        }
        return res;
    }
}

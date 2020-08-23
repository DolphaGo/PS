import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        st=new StringTokenizer(br.readLine());
        int[] arr=new int[n];
        arr[0]=Integer.parseInt(st.nextToken());
        int answer=arr[0];
        for(int i=1;i<n;i++) {
            int val=Integer.parseInt(st.nextToken());
            if(val<arr[i-1]+val) arr[i]=arr[i-1]+val;
            else arr[i]=val;
            answer=Math.max(answer,arr[i]);
        }
        System.out.println(answer);
    }
}
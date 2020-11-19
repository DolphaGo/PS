import java.io.*;
import java.util.*;

class Main {
    static void conquer(int[] arr,int s,int e) {
        int m=(s+e)>>1;
        int start=s;
        int end=m+1;
        int[] buffer=new int[e-s+1];
        int idx=0;

        while(start<=m && end<=e) {
            if(arr[start]<arr[end]) buffer[idx++]=arr[start++];
            else buffer[idx++]=arr[end++];
        }

        while(start<=m) buffer[idx++]=arr[start++];
        while(end<=e) buffer[idx++]=arr[end++];

        for(int i=s;i<=e;i++) {
            arr[i]=buffer[i-s];
        }

    }
    static void divide(int[] arr,int s,int e) {
        if(s<e) {
            int m=(s+e)>>1;
            divide(arr,s,m);
            divide(arr,m+1,e);
            conquer(arr,s,e);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=Integer.parseInt(br.readLine());
        }

        divide(arr,0,n-1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);

    }
}
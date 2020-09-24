import java.io.*;
import java.util.*;

public class Main {
    static int[] arr,tree;
    static int init(int node,int start,int end){
        if(start==end){
            return tree[node]=arr[start];
        }
        int m=(start+end)>>1;
        return tree[node]=Math.max(init(node*2,start,m),init(node*2+1,m+1,end));
    }
    static int query(int node,int start,int end,int left,int right){
        if(left>end || right<start) return 0;

        if(left<=start && end<=right){
            return tree[node];
        }
        int m=(start+end)>>1;
        return Math.max(query(node*2,start,m,left,right),query(node*2+1,m+1,end,left,right));
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        arr=new int[n+1];
        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) arr[i]=Integer.parseInt(st.nextToken());

        int h=(int)Math.ceil(Math.log(n)/Math.log(2));
        tree=new int[1<<h+1];
        init(1,1,n);

        StringBuilder sb=new StringBuilder();
        for(int i=m;i<=n-m+1;i++){
            sb.append(query(1,1,n,i-m+1,i+m-1)).append(" ");
        }
        System.out.print(sb);
    }
}
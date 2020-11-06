import java.io.*;
import java.util.*;

public class Main {
    static int[] tree;
    static final int MAX=1<<16;
    static int update(int node,int start,int end,int idx,int diff){
        if(idx<start || idx>end) return tree[node];
        if(start==end){
            return tree[node]+=diff;
        }
        int m=(start+end)>>1;
        return tree[node]=update(node<<1,start,m,idx,diff)+update(node<<1|1,m+1,end,idx,diff);
    }
    static long query(int node,int start,int end,int res){
        if(start==end) return start;
        int m=(start+end)>>1;
        int L=tree[node<<1];
        if(L>=res) return query(node<<1,start,m,res);
        else return query(node<<1|1,m+1,end,res-L);
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());

        int[] arr=new int[n];
        int h=(int)Math.ceil(Math.log(MAX+1)/Math.log(2));
        tree=new int[1<<h+1];

        // 구간의 리프노드로 활용할 것
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }

        // k-1개 전처리
        for(int i=0;i<k-1;i++){
            update(1,0,MAX,arr[i],1);
        }

        long answer=0;
        //슬라이딩 방식으로 중앙값 구하기
        for(int i=k-1;i<n;i++){
            update(1,0,MAX,arr[i],1);
            answer+=query(1,0,MAX,(k+1)/2);
            update(1,0,MAX,arr[i-(k-1)],-1);
        }
        System.out.println(answer);
    }
}
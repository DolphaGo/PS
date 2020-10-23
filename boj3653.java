import java.io.*;
import java.util.*;

public class Main {
    static int[] tree;
    static int query(int node,int start,int end,int left,int right){
        if(right<start || left>end) return 0;

        if(left<=start &&end<=right) return tree[node];

        int m=(start+end)>>1;
        return query(node<<1,start,m,left,right)+query(node<<1|1,m+1,end,left,right);
    }
    static int update(int node,int start,int end,int idx,int val){
        if(start>idx || end<idx) return tree[node];

        if(start==end) return tree[node]+=val;

        int m=(start+end)>>1;
        return tree[node]=update(node<<1,start,m,idx,val)+update(node<<1|1,m+1,end,idx,val);
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++){
            st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());
            int m=Integer.parseInt(st.nextToken());
            int[] pos =new int[n+m+1];
            tree=new int[4*(n+m)];
            for(int i=m+1;i<=m+n;i++) {
                update(1,1,n+m,i,1);
                pos[i-m]=i; //1번은 m+1번째에 위치...n번은 m+n번째에 위치
            }

            int start=m;
            st=new StringTokenizer(br.readLine());
            for(int i=0;i<m;i++){
                int num=Integer.parseInt(st.nextToken());
                int p=pos[num];
                sb.append(query(1,1,n+m,start,p-1)).append(" ");
                //맨 앞으로 당겨오는 로직
                pos[num]=start--;
                update(1,1,n+m,p,-1);//기존 위치를 지워주고
                update(1,1,n+m,pos[num],1);//새로운 위치로 업데이트
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
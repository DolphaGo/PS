import java.io.*;
import java.util.*;

public class Main {
    static int update(int[] tree,int node,int start,int end,int idx,int val){
        if(idx<start || idx>end) return tree[node];

        if(start==end){
            return tree[node]+=val;
        }

        int m=(start+end)>>1;
        return tree[node]=update(tree,node<<1,start,m,idx,val)+update(tree,node<<1|1,m+1,end,idx,val);
    }
    static int query(int[] tree,int node,int start,int end,int left,int right){
        if(end<left || start >right) return 0;

        if(left<=start&&end<=right) return tree[node];
        int m=(start+end)>>1;
        return query(tree,node<<1,start,m,left,right)+query(tree,node<<1|1,m+1,end,left,right);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        int h=(int)Math.ceil(Math.log(n)/Math.log(2));

        boolean[] even=new boolean[n+1];
        int[] tree=new int[1<<h+1];
        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            int val=Integer.parseInt(st.nextToken());
            if(val%2==0) {
                even[i]=true;
                update(tree,1,1,n,i,1);
            }
        }
        int m=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int q=Integer.parseInt(st.nextToken());
            if(q==1){
                int idx=Integer.parseInt(st.nextToken());
                int x=Integer.parseInt(st.nextToken());
                if(even[idx] && x%2!=0){ //기존 : 짝수, 새로운 값 : 홀수
                    even[idx]=false;
                    update(tree,1,1,n,idx,-1);
                }else if( !even[idx] && x%2==0){ // 기존 : 홀수, 새로운 값 : 짝수
                    even[idx]=true;
                    update(tree,1,1,n,idx,1);
                }
            }else{
                int l=Integer.parseInt(st.nextToken());
                int r=Integer.parseInt(st.nextToken());
                if(q==2) sb.append(query(tree,1,1,n,l,r));
                else sb.append((r-l+1)-query(tree,1,1,n,l,r));
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}
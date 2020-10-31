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

        int[] arr=new int[n+1];
        int[] evenTree=new int[1<<h+1];
        int[] oddTree=new int[1<<h+1];
        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            arr[i]=Integer.parseInt(st.nextToken());
            if(arr[i]%2==0) update(evenTree,1,1,n,i,1);
            else update(oddTree,1,1,n,i,1);
        }
        int m=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int q=Integer.parseInt(st.nextToken());
            if(q==1){
                int idx=Integer.parseInt(st.nextToken());
                int x=Integer.parseInt(st.nextToken());
                if(arr[idx]%2==0 && x%2!=0){ //기존 : 짝수, 새로운 값 : 홀수
                    update(evenTree,1,1,n,idx,-1);
                    update(oddTree,1,1,n,idx,1);
                    arr[idx]=x;
                }else if(arr[idx]%2!=0 && x%2==0){ // 기존 : 홀수, 새로운 값 : 짝수
                    update(oddTree,1,1,n,idx,-1);
                    update(evenTree,1,1,n,idx,1);
                    arr[idx]=x;
                }
            }else{
                int l=Integer.parseInt(st.nextToken());
                int r=Integer.parseInt(st.nextToken());
                if(q==2) sb.append(query(evenTree,1,1,n,l,r));
                else sb.append(query(oddTree,1,1,n,l,r));
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}
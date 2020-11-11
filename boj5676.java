import java.io.*;
import java.util.*;

class Main {
    static int[] tree;
    static int update(int node,int start,int end,int idx,int val){
        if(idx>end || idx<start) return tree[node];
        if(start==end){
            return tree[node]=val;
        }
        int m=(start+end)>>1;
        return tree[node]=update(node<<1,start,m,idx,val)*update(node<<1|1,m+1,end,idx,val);
    }
    static int query(int node,int start,int end,int left,int right){
        if(end<left || right<start) return 1;
        if(left<=start && end<=right) return tree[node];
        int m=(start+end)>>1;
        return query(node<<1,start,m,left,right)*query(node<<1|1,m+1,end,left,right);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String s;
        StringBuilder sb=new StringBuilder();
        while((s=br.readLine())!=null){
            st=new StringTokenizer(s);
            int n=Integer.parseInt(st.nextToken());
            int k=Integer.parseInt(st.nextToken());
            int h=(int)Math.ceil(Math.log(n)/Math.log(2));
            tree=new int[1<<h+1];
            Arrays.fill(tree,1);

            st=new StringTokenizer(br.readLine());
            for(int i=1;i<=n;i++){
                int val=Integer.parseInt(st.nextToken());
                if(val!=0){
                    if(val>0) val=1;
                    else val=-1;
                }
                update(1,1,n,i,val);
            }

            for(int i=0;i<k;i++){
                st=new StringTokenizer(br.readLine());
                char c=st.nextToken().charAt(0);
                if(c=='C'){ //명령
                    int idx=Integer.parseInt(st.nextToken());
                    int v=Integer.parseInt(st.nextToken());
                    if(v>0) v=1;
                    else if(v<0) v=-1;
                    update(1,1,n,idx,v);
                }else{ //곱셈
                    int a=Integer.parseInt(st.nextToken());
                    int b=Integer.parseInt(st.nextToken());
                    int res=query(1,1,n,a,b);
                    if(res<0) sb.append("-");
                    else if(res>0) sb.append("+");
                    else sb.append("0");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
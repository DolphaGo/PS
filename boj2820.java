import java.io.*;
import java.util.*;

public class Main {
    static long[] lazy,arr,tree;
    static int[] l,r;
    static int o=0;
    static List<Integer>[] list;

    static void lazyUpdate(int node,int start,int end){
        if(lazy[node]!=0){
            tree[node]+=lazy[node];
            if(start!=end){
                lazy[node*2]+=lazy[node];
                lazy[node*2+1]+=lazy[node];
            }
            lazy[node]=0;
        }
    }
    static void update(int node,int start,int end,int left,int right,long diff){
        lazyUpdate(node,start,end);
        if(start> right || end < left) return;
        if(left<=start && end<= right){
            tree[node]+=diff;
            if(start!=end){
                lazy[node*2]+=diff;
                lazy[node*2+1]+=diff;
            }
            return;
        }
        int m=(start+end)>>1;
        update(node*2,start,m,left,right,diff);
        update(node*2+1,m+1,end,left,right,diff);
    }

    static long query(int node,int start,int end,int left,int right){
        lazyUpdate(node,start,end);
        if(left >end || right <start) return 0;
        if(left<=start&&end<=right) return tree[node];
        int m=(start+end)>>1;
        return query(node*2,start,m,left,right)+query(node*2+1,m+1,end,left,right);
    }

    static void dfs(int now){
        l[now]= ++o;
        for(int next:list[now]) dfs(next);
        r[now]=o;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        arr=new long[n+1];
        tree=new long[4*n];
        lazy=new long[4*n];
        l=new int[n+1];
        r=new int[n+1];
        list=new ArrayList[n+1];
        for(int i=1;i<=n;i++) list[i]=new ArrayList<>();

        arr[1]=Integer.parseInt(br.readLine());
        for(int i=2;i<=n;i++){
            st=new StringTokenizer(br.readLine());
            arr[i]=Integer.parseInt(st.nextToken());
            int x=Integer.parseInt(st.nextToken());
            list[x].add(i);
        }
        dfs(1);

        for(int i=1;i<=n;i++) update(1,1,n,l[i],l[i],arr[i]);

        StringBuilder sb=new StringBuilder();
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            char c=st.nextToken().charAt(0);
            int a=Integer.parseInt(st.nextToken());
            if(c=='p'){
                long diff=Long.parseLong(st.nextToken());
                update(1,1,n,l[a]+1,r[a],diff);
            }else{
                sb.append(query(1,1,n,l[a],l[a])).append("\n");
            }
        }
        System.out.print(sb);
    }
}
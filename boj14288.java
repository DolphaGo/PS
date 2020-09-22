import java.io.*;
import java.util.*;
public class Main {
    static List<Integer> list[];
    static int[] l,r;
    static int o=0;

    static void lazyUpdate(int[] tree,int[] lazy, int node,int start,int end){
        if(lazy[node]!=0){
            tree[node]+=(end-start+1)*lazy[node];
            if(start!=end){
                lazy[node*2]+=lazy[node];
                lazy[node*2+1]+=lazy[node];
            }
            lazy[node]=0;
        }
    }
    static void update(int[] tree,int[] lazy, int node,int start,int end,int left,int right,int w){
        lazyUpdate(tree,lazy,node,start,end);
        if(start>right || end < left) return ;

        if(left<=start && end <=right){
            tree[node] += (end-start+1)*w;
            if(start!=end){
                lazy[node*2]+=w;
                lazy[node*2+1]+=w;
            }
            return;
        }
        int m=(start+end)>>1;
        update(tree,lazy,node*2,start,m,left,right,w);
        update(tree,lazy,node*2+1,m+1,end,left,right,w);
        tree[node]=tree[node*2]+tree[node*2+1];
    }

    static int query(int[] tree,int[] lazy,int node,int start,int end,int left,int right){
        lazyUpdate(tree,lazy,node,start,end);
        if(start>right || end < left) return 0;

        if(left<= start && end<= right){
            return tree[node];
        }
        int m=(start+end)>>1;
        return query(tree,lazy,node*2,start,m,left,right) + query(tree,lazy,node*2+1,m+1,end,left,right);
    }

    static void dfs(int now){
        l[now]=++o;
        for(int next:list[now]) dfs(next);
        r[now]=o;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        st=new StringTokenizer(br.readLine());
        list=new ArrayList[n+1];
        for(int i=1;i<=n;i++) list[i]=new ArrayList<>();
        st.nextToken();
        for(int i=2;i<=n;i++){
            int senior=Integer.parseInt(st.nextToken());
            list[senior].add(i);
        }
        l=new int[n+1];
        r=new int[n+1];
        dfs(1);
        int[] tree=new int[4*n];
        int[] tree2=new int[4*n];
        int[] lazy=new int[4*n];
        int[] lazy2=new int[4*n];
        boolean state=false;
        StringBuilder sb=new StringBuilder();
        for(int j=0;j<m;j++){
            st=new StringTokenizer(br.readLine());
            int q=Integer.parseInt(st.nextToken());
            if(q==1){
                int i=Integer.parseInt(st.nextToken());
                int w=Integer.parseInt(st.nextToken());
                if(state) update(tree,lazy,1,1,n,l[i],l[i],w);
                else update(tree2,lazy2,1,1,n,l[i],r[i],w);
            }else if(q==2){
                int i=Integer.parseInt(st.nextToken());
                int a=query(tree,lazy,1,1,n,l[i],r[i]);
                int b=query(tree2,lazy2,1,1,n,l[i],l[i]);
                sb.append(a+b).append("\n");
            }else state=!state;
        }
        System.out.print(sb);
    }
}
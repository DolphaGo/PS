import java.io.*;
import java.util.*;
public class Main {
    static List<Integer> list[];
    static int[] l,r,tree,lazy;
    static int o=0;

    static void lazyUpdate(int node,int start,int end){
        if(lazy[node]!=0){
            tree[node]+=(end-start+1)*lazy[node];
            if(start!=end){
                lazy[node*2]+=lazy[node];
                lazy[node*2+1]+=lazy[node];
            }
            lazy[node]=0;
        }
    }
    static void update(int node,int start,int end,int left,int right,int w){
        lazyUpdate(node,start,end);
        if(start>right || end < left) return ;

        //사실상 리프노드에만 적용됨. left==right로만 들어오기 때문(리프노드 업데이트)
        if(left<=start && end <=right){
            tree[node] += (end-start+1)*w;
            if(start!=end){
                lazy[node*2]+=w;
                lazy[node*2+1]+=w;
            }
            return;
        }
        int m=(start+end)>>1;
        update(node*2,start,m,left,right,w);
        update(node*2+1,m+1,end,left,right,w);
        tree[node]=tree[node*2]+tree[node*2+1];
    }

    static int query(int node,int start,int end,int left,int right){
        lazyUpdate(node,start,end);
        if(start>right || end < left) return 0;

        if(left<= start && end<= right){
            return tree[node];
        }
        int m=(start+end)>>1;
        return query(node*2,start,m,left,right) + query(node*2+1,m+1,end,left,right);
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
        tree=new int[4*n];
        lazy=new int[4*n];

        StringBuilder sb=new StringBuilder();
        for(int j=0;j<m;j++){
            st=new StringTokenizer(br.readLine());
            int q=Integer.parseInt(st.nextToken());
            int i=Integer.parseInt(st.nextToken());
            if(q==1){
                int w=Integer.parseInt(st.nextToken());
                update(1,1,n,l[i],l[i],w);
            }else {
                sb.append(query(1,1,n,l[i],r[i])).append("\n");
            }
        }
        System.out.print(sb);
    }
}
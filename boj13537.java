import java.util.*;
import java.io.*;

public class Main {
    static int[] arr;
    static List<Integer> tree[];

    static void update(int node, int start, int end, int i, int val){
        //i번째 값을 입력받았는데 start~end 범위에 i가 없을 경우
        if(i<start || i > end) return;
        tree[node].add(val);
        if(start!=end){
            int m=(start+end)>>1;
            update(node*2,start,m,i,val);
            update(node*2+1,m+1,end,i,val);
        }
    }

    static int query(int node,int start,int end,int left,int right,int k){
        if(left>end || right<start) return 0;

        if(left<=start && end<=right){
            int s=0;
            int e=tree[node].size();
            while(s<e){
                int m=(s+e)>>1;
                if(tree[node].get(m)<=k) s=m+1;
                else e=m;
            }
            return tree[node].size()-e;
        }
        int m=(start+end)>>1;
        return query(node*2,start,m,left,right,k)+query(node*2+1,m+1,end,left,right,k);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        int h=(int)Math.ceil(Math.log(n)/Math.log(2));

        arr=new int[n+1];
        tree=new ArrayList[1<<h+1];
        for(int i=1;i<tree.length;i++) tree[i]=new ArrayList<>();

        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) {
            arr[i]=Integer.parseInt(st.nextToken());
            update(1,1,n,i,arr[i]);
        }

        for(int i=1;i<tree.length;i++) {
            Collections.sort(tree[i]);
        }

        int m=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int k=Integer.parseInt(st.nextToken());
            sb.append(query(1,1,n,a,b,k)).append("\n");
        }
        System.out.print(sb);
    }
}
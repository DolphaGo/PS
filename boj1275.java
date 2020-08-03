import java.util.*;
import java.io.*;
public class Main {
    static long[] arr,tree;

    static long init(int node,int start, int end){
        if(start==end){
            return tree[node]=arr[start];
        }
        int m=(start+end)>>1;
        return tree[node]=init(node*2,start,m)+init(node*2+1,m+1,end);
    }
    static long query(int node,int start,int end,int left,int right){
        if(end<left || right<start) return 0;
        if(left<=start&&end<=right){
            return tree[node];
        }
        int m=(start+end)>>1;
        return query(node*2,start,m,left,right)+query(node*2+1,m+1,end,left,right);
    }
    static void update(int node,int start,int end,int index,long diff){
        //범위 밖에 있는 경우
        if(index<start || index>end) return;

        tree[node]+=diff;
        if(start==end) return;
        int m=(start+end)>>1;
        update(node*2,start,m,index,diff);
        update(node*2+1,m+1,end,index,diff);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken());
        int Q=Integer.parseInt(st.nextToken());

        arr=new long[N+1];
        int h=(int)Math.ceil(Math.log(N)/Math.log(2));
        tree=new long[1<<(h+1)];

        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            arr[i]=Long.parseLong(st.nextToken());
        }

        init(1,1,N);
        StringBuilder sb=new StringBuilder();
        for(int q=0;q<Q;q++){
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            if(x>y){
                x^=y; y^=x; x^=y;
            }
            sb.append(query(1,1,N,x,y)+"\n");

            int a=Integer.parseInt(st.nextToken());
            long val=arr[a];
            long b=Long.parseLong(st.nextToken());
            update(1,1,N,a,b-val);
            arr[a]=b;
        }
        System.out.print(sb.toString());
    }
}


import java.io.*;
import java.util.*;
public class Main{
    static int last_ans, n;
    static List<Integer> list[];
    static int[] arr;

    static int upperBound(int i,int x){
        int s=0;
        int e=list[i].size();
        while(s<e){
            int m=(s+e)>>1;
            if(list[i].get(m)<=x) s=m+1;
            else e=m;
        }
        return e;
    }

    static void update(int node,int start,int end,int i,int val){
        if(start> i || end < i ) return;
        if(list[node]==null) list[node]=new ArrayList<>();
        list[node].add(val);
        if(start!=end){
            int m=(start+end)>>1;
            update(node*2,start,m,i,val);
            update(node*2+1,m+1,end,i,val);
        }
    }

    static int query(int node,int start,int end,int left,int right,int k){
        if(start>right || end<left) return 0;

        if(left<=start && end<= right){
            int idx=upperBound(node,k);
            return list[node].size()-idx;
        }

        int m=(start+end)>>1;
        return query(node*2,start,m,left,right,k)+query(node*2+1,m+1,end,left,right,k);

    }
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n=Integer.parseInt(br.readLine());
        arr=new int[n+1];
        st=new StringTokenizer(br.readLine());
        list=new ArrayList[4*n];
        for(int i=1;i<=n;i++){
            arr[i]=Integer.parseInt(st.nextToken());
            update(1,1,n,i,arr[i]);
        }

        for(int i=1;i<list.length;i++){
            if(list[i]!=null) Collections.sort(list[i]);
        }


        StringBuilder sb=new StringBuilder();
        int m=Integer.parseInt(br.readLine());
        last_ans=0;
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken())^last_ans;
            int b=Integer.parseInt(st.nextToken())^last_ans;
            int c=Integer.parseInt(st.nextToken())^last_ans;
            last_ans=query(1,1,n,a,b,c);
            sb.append(last_ans).append("\n");
        }
        System.out.print(sb);
    }
}
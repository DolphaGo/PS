import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] tree;
    static void update(int node,int start,int end,int p){
        if(p<start || p>end) return ;
        if(start==end){
            tree[node]++;
            return;
        }
        int m=(start+end)>>1;
        update(node<<1,start,m,p);
        update(node<<1 | 1,m+1,end,p);
        tree[node]=tree[node<<1]+tree[node<<1 | 1];
    }

    static int query(int node,int start,int end,int left,int right){
        if(start>right || end<left) return 0;
        if(left<=start && end<=right) return tree[node];
        int m=(start+end)>>1;
        return query(node<<1,start,m,left,right)+query(node<<1|1,m+1,end,left,right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());

        int h=(int)Math.ceil(Math.log(2*n)/Math.log(2));
        tree=new int[1<<h+1];
        int[][] arr=new int[n+1][2];
        for(int i=1;i<=2*n;i++){
            int val=Integer.parseInt(br.readLine());
            if(arr[val][0]==0) arr[val][0]=i;
            else arr[val][1]=i;
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0],o2[0]);
            }
        });

        int answer=0;
        for(int i=1;i<=n;i++){
            answer +=query(1,1,2*n,arr[i][0],arr[i][1]);
            update(1,1,2*n, arr[i][1]);
        }

        System.out.println(answer);
    }
}
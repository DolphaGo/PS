import java.io.*;
import java.util.*;

public class Main {
    static int[] tree;
    static void update(int node,int start,int end,int idx,int diff){
        if(start>idx || end<idx) return;
        tree[node]+=diff;
        if(start!=end) {
            int m = (start + end) >> 1;
            update(node << 1, start, m, idx,diff);
            update(node << 1 | 1, m + 1, end, idx,diff);
        }
    }
    static int query(int node,int start,int end,int left,int right){
        if(left>end || right<start) return 0;
        if(left<=start &&end<=right){
            return tree[node];
        }
        int m=(start+end)>>1;
        return query(node<<1,start,m,left,right)+query(node<<1|1,m+1,end,left,right);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());

        tree=new int[4*n];
        int[][] arr=new int[n][2];
        for(int i=0;i<n;i++) {
            arr[i][0]=Integer.parseInt(br.readLine());
            arr[i][1]=i+1;
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0],o2[0]);
            }
        });

        int[] answer=new int[n+1];
        for(int i=0;i<n;i++){
            int idx=arr[i][1]; //현재 위치
            int overTake=query(1,1,n,1,idx); //따라 잡을 수 있는 사람들의 수
            answer[idx]= idx-overTake;
            update(1,1,n,idx,1);
        }

        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++) sb.append(answer[i]).append("\n");
        System.out.print(sb);
    }
}
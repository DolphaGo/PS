import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n=Integer.parseInt(br.readLine());
        int m=Integer.parseInt(br.readLine());
        int[] ans=new int[n+1];
        int[] indegree=new int[n+1];
        int[] mul=new int[n+1];
        mul[n]=1;

        ArrayList<int[]> list[]=new ArrayList[n+1];
        for(int i=1;i<=n;i++) list[i]=new ArrayList<>();

        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            int k=Integer.parseInt(st.nextToken());
            list[x].add(new int[]{y,k});
            indegree[y]++;
        }
        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0],o2[0]);
            }
        });
        Queue<Integer> q=new LinkedList<>();
        q.add(n);
        while(!q.isEmpty()){
            int p=q.poll();
            //부품이라면
            if(list[p].size()==0){
                ans[p]+=mul[p];
                pq.add(new int[]{p,ans[p]});
                continue;
            }
            
            for(int i=0;i<list[p].size();i++) {
                int[] child = list[p].get(i);
                int ci = child[0];
                indegree[ci]--;
                mul[ci]+=mul[p]*child[1];
                if(indegree[ci]==0) q.add(ci);
            }
        }
        StringBuilder sb=new StringBuilder();
        while(!pq.isEmpty()) {
            int[] p=pq.poll();
            sb.append(p[0]+" "+p[1]+"\n");
        }
        System.out.print(sb);
    }
}
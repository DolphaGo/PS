import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N=Integer.parseInt(br.readLine());
        ArrayList<Integer> list[]=new ArrayList[N+1];
        int[] p=new int[N+1];
        for(int i=1;i<=N;i++) {
            list[i]=new ArrayList<>();
            p[i]=i;
        }

        for(int i=0;i<N-1;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }
        boolean[] check=new boolean[N+1];
        //1번은 항상 root => 정해진 애들부터 시작
        Queue<Integer> q=new LinkedList<>();
        check[1]=true;
        q.add(1);
        while(!q.isEmpty()){
            int val=q.poll();
            for(int i=0;i<list[val].size();i++){
                int nval=list[val].get(i);
                if(!check[nval]){
                    check[nval]=true;
                    p[nval]=val;
                    q.add(nval);
                }
            }
        }
        StringBuilder sb=new StringBuilder();
        for(int i=2;i<=N;i++) sb.append(p[i]+"\n");
        System.out.print(sb);
    }
}


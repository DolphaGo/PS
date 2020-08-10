import java.util.*;
import java.io.*;
public class Main {
    static HashMap<Integer,Integer> map[];
    static int n,f1,f2;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        map=new HashMap[n+1];
        for(int i=1;i<=n;i++) map[i]=new HashMap<>();

        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            if(map[a].get(b)==null) map[a].put(b,c);
            else map[a].put(b,Math.max(map[a].get(b),c));

            if(map[b].get(a)==null) map[b].put(a,c);
            else map[b].put(a,Math.max(map[b].get(a),c));

        }
        st=new StringTokenizer(br.readLine());
        f1=Integer.parseInt(st.nextToken());
        f2=Integer.parseInt(st.nextToken());

        int answer=0;
        int s=1;
        int e=1000000000;
        while(s<=e){
            int mid=(s+e)>>1;
            if(test(mid)){
                answer=Math.max(answer,mid);
                s=mid+1;
            }else e=mid-1;
        }
        System.out.println(answer);
    }
    static boolean test(int v){
        Queue<Integer> q=new LinkedList<>();
        boolean visit[]=new boolean[n+1];
        visit[f1]=true;
        q.add(f1);
        while(!q.isEmpty()){
            int now=q.poll();
            if(now==f2) return true;

            for(int next:map[now].keySet()){
                int stand=map[now].get(next);
                if(!visit[next]&&v<=stand){
                    visit[next]=true;
                    q.add(next);
                }
            }
        }
        return false;
    }
}


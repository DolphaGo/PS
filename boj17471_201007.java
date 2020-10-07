import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] count;
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n=Integer.parseInt(br.readLine());
        count=new int[n+1];
        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) count[i]=Integer.parseInt(st.nextToken());

        list=new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            list[i]=new ArrayList<>();
            st=new StringTokenizer(br.readLine());
            int cnt=Integer.parseInt(st.nextToken());
            for(int j=0;j<cnt;j++){
                int next=Integer.parseInt(st.nextToken());
                list[i].add(next);
            }
        }

        ArrayList<Integer> teamA=new ArrayList<>();
        ArrayList<Integer> teamB=new ArrayList<>();

        int answer=Integer.MAX_VALUE;
        for(int i=1;i<(1<<(n-1));i++){
            int sumA=0;
            int sumB=0;
            for(int j=0;j<n;j++){
                if((i&(1<<j))>0){
                    teamA.add(j+1);
                    sumA+=count[j+1];
                }
                else{
                    teamB.add(j+1);
                    sumB+=count[j+1];
                }
            }
            if(isConnected(teamA)&&isConnected(teamB)) answer=Math.min(answer,Math.abs(sumA-sumB));

            teamA.clear(); teamB.clear();
        }
        System.out.println(answer==Integer.MAX_VALUE?-1:answer);
    }

    //두 팀 모두 각각 인접해있는지 확인하는 메서드
    static boolean isConnected(List<Integer> team){
        Queue<Integer> q=new LinkedList<>();
        boolean[] visit=new boolean[n+1];
        int check=team.size();
        q.add(team.get(0));
        visit[team.get(0)]=true;
        --check;
        while(!q.isEmpty()){
            int now=q.poll();
            for(int next:list[now]){
                if(team.contains(next) && !visit[next]){
                    visit[next]=true;
                    q.add(next);
                    --check;
                }
            }
        }
        return check == 0;
    }
}
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        int[] arr=new int[101];
        for(int i=0;i<n+m;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            arr[a]=b;
        }
        boolean[] visit=new boolean[101];
        int answer=Integer.MAX_VALUE;

        PriorityQueue<int[]> q=new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] o1,int[] o2){
                return Integer.compare(o1[1],o2[1]);
            }
        });

        q.add(new int[]{1,0});
        while(!q.isEmpty()){
            int[] p=q.poll();
            int now=p[0];

            if(visit[now]) continue;
            visit[now]=true;
            if(now==100){
                System.out.println(p[1]);
                return;
            }

            if(arr[now]!=0){ //정해진 길이 있으면
                int next=arr[now];
                q.add(new int[]{next,p[1]}); //주사위를 던진게 아니기에 숫자는 올라가지 않음.
            }else{
                for(int jump=1;jump<=6;jump++){
                    int next=now+jump;
                    if(next<=100 && !visit[next]){
                        q.add(new int[]{next,p[1]+1});
                    }
                }
            }
        }

    }
}


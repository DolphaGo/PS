import java.io.*;
import java.util.*;

public class Main {
    static int n,l,r;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        l=Integer.parseInt(st.nextToken());
        r=Integer.parseInt(st.nextToken());
        arr =new int[n][n];

        for(int y=0;y<n;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<n;x++){
                arr[y][x]=Integer.parseInt(st.nextToken());
            }
        }

        int answer=0;
        while(go()) ++answer;
        System.out.println(answer);
    }
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    static boolean go(){
        int cnt=0;
        boolean[][] visit=new boolean[n][n];
        Queue<int[]> q=new LinkedList<>();
        Queue<int[]> trace=new LinkedList<>();
        for(int y=0;y<n;y++){
            for(int x=0;x<n;x++){
                if(!visit[y][x]){
                    ++cnt;
                    int sum=arr[y][x];
                    q.add(new int[]{y,x});
                    trace.add(new int[]{y,x});
                    visit[y][x]=true;
                    while(!q.isEmpty()){
                        int[] p=q.poll();
                        for(int i=0;i<4;i++){
                            int ny=p[0]+dy[i];
                            int nx=p[1]+dx[i];
                            if(isRange(ny,nx)&& union(p[0],p[1],ny,nx)&&!visit[ny][nx]) {
                                visit[ny][nx] = true;
                                q.add(new int[]{ny, nx});
                                trace.add(new int[]{ny,nx});
                                sum += arr[ny][nx];
                            }
                        }
                    }
                    int avg=sum/trace.size();
                    while(!trace.isEmpty()){
                        int[] p=trace.poll();
                        arr[p[0]][p[1]]=avg;
                    }
                }
            }
        }
        return cnt<n*n;
    }
    static boolean isRange(int y,int x){
        return 0<=y&&y<n&&0<=x&&x<n;
    }
    static boolean union(int y, int x, int ny, int nx){
        int prev=arr[y][x];
        int next=arr[ny][nx];
        int diff=Math.abs(prev-next);
        return l<=diff&&diff<=r;
    }
}
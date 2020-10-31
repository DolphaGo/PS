import java.util.LinkedList;
import java.util.Queue;

class Solution2{
    static int n;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    public int[] solution(int[][] v){
        n=v.length;

        Queue<int[]> q=new LinkedList<>();
        boolean[][] visit=new boolean[n][n];

        int[] answer=new int[3];
        for(int y=0;y<n;y++){
            for(int x=0;x<n;x++){
                if(!visit[y][x]){
                    visit[y][x]=true;
                    int num=v[y][x];
                    answer[num]++;
                    q.add(new int[]{y,x});
                    while(!q.isEmpty()){
                        int[] p=q.poll();
                        for(int i=0;i<4;i++){
                            int ny=p[0]+dy[i];
                            int nx=p[1]+dx[i];
                            if(isRange(ny,nx)&&v[ny][nx]==num&&!visit[ny][nx]){
                                visit[ny][nx]=true;
                                q.add(new int[]{ny,nx});
                            }
                        }
                    }
                }
            }
        }
        return answer;
    }
    static boolean isRange(int y,int x){
        return 0<=y&&y<n&&0<=x&&x<n;
    }
}
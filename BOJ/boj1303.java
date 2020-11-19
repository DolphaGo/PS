import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int w=Integer.parseInt(st.nextToken());
        int h=Integer.parseInt(st.nextToken());

        char[][] map=new char[h][w];
        for(int y=0;y<h;y++){
            map[y]=br.readLine().toCharArray();
        }

        int whiteScore=0;
        int blueScore=0;

        int dy[]={-1,1,0,0};
        int dx[]={0,0,-1,1};

        Queue<int[]> q=new LinkedList<>();
        boolean[][] visit=new boolean[h][w];
        for(int y=0;y<h;y++){
            for(int x=0;x<w;x++){
                if(!visit[y][x]){
                    visit[y][x]=true;
                    char c=map[y][x];
                    int count=1;
                    q.add(new int[]{y,x});
                    while(!q.isEmpty()){
                        int[] p=q.poll();
                        for(int i=0;i<4;i++){
                            int ny=p[0]+dy[i];
                            int nx=p[1]+dx[i];
                            if(ny>=0&&nx>=0&&ny<h&&nx<w&&!visit[ny][nx]&&map[ny][nx]==c){
                                visit[ny][nx]=true;
                                ++count;
                                q.add(new int[]{ny,nx});
                            }
                        }
                    }
                    int score=count*count;
                    if(c=='W') whiteScore+=score;
                    else blueScore+=score;
                }
            }
        }
        System.out.println(whiteScore+" "+blueScore);
    }
}

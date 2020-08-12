import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int h=Integer.parseInt(st.nextToken());
        int w=Integer.parseInt(st.nextToken());

        int[][] map=new int[h][w];
        for(int y=0;y<h;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<w;x++){
                map[y][x]=Integer.parseInt(st.nextToken());
            }
        }

        int cnt=0;
        int maxArea=0;
        boolean[][] visit=new boolean[h][w];
        Queue<int[]> q=new LinkedList<>();
        int[] dy={-1,1,0,0};
        int[] dx={0,0,-1,1};

        for(int y=0;y<h;y++){
            for(int x=0;x<w;x++){
                if(map[y][x]==1&&!visit[y][x]){
                    ++cnt; //그림의 수
                    int area=1; //그림의 넓이
                    visit[y][x]=true;
                    q.add(new int[]{y,x});
                    while(!q.isEmpty()){
                        int[] p=q.poll();
                        for(int i=0;i<4;i++){
                            int ny=p[0]+dy[i];
                            int nx=p[1]+dx[i];
                            if(ny>=0&&nx>=0&&ny<h&&nx<w&&map[ny][nx]==1&&!visit[ny][nx]){
                                visit[ny][nx]=true;
                                q.add(new int[]{ny,nx});
                                area++;
                            }
                        }
                    }
                    maxArea=maxArea<area?area:maxArea;
                }
            }
        }
        System.out.println(cnt+"\n"+maxArea);
    }
}

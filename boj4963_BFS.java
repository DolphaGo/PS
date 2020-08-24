import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int dy[]={-1,1,0,0,-1,1,-1,1};
        int dx[]={0,0,-1,1,-1,1,1,-1};
        StringBuilder sb=new StringBuilder();
        Queue<int[]> q=new LinkedList<>();
        while(true){
            st=new StringTokenizer(br.readLine());
            int w=Integer.parseInt(st.nextToken());
            int h=Integer.parseInt(st.nextToken());
            if(w==0&&h==0) break;

            int[][] map=new int[h][w];
            for(int y=0;y<h;y++){
                st=new StringTokenizer(br.readLine());
                for(int x=0;x<w;x++){
                    map[y][x]=Integer.parseInt(st.nextToken());
                }
            }

            int answer=0;
            boolean[][] visit=new boolean[h][w];
            for(int y=0;y<h;y++){
                for(int x=0;x<w;x++){
                    if(map[y][x]==1&&!visit[y][x]){
                        visit[y][x]=true;
                        q.add(new int[]{y,x});
                        ++answer;
                        while(!q.isEmpty()){
                            int[] p=q.poll();
                            for(int i=0;i<8;i++){
                                int ny=p[0]+dy[i];
                                int nx=p[1]+dx[i];
                                if(ny>=0&&nx>=0&&ny<h&&nx<w&&map[ny][nx]==1&&!visit[ny][nx]){
                                    visit[ny][nx]=true;
                                    q.add(new int[]{ny,nx});
                                }
                            }

                        }
                    }
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
}
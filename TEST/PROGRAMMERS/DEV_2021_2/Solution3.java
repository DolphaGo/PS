import java.util.*;

class Solution {
    static int[][] map = new int[6][6];
    public String[] solution(int[][] macaron) {

        for(int[] m : macaron){
            int idx = m[0];
            int color = m[1];
            move(idx, color);
            while(boom()){
                letdown();
            }
        }

        StringBuilder sb= new StringBuilder();
        String[] answer = new String[6];
        for(int y=0;y<6;y++){
            for(int x=0;x<6;x++){
                sb.append(map[y][x]);
            }
            answer[y] = sb.toString();
            sb.setLength(0);
        }
        return answer;
    }

    private void move(int idx,int color){
        int y=-1;
        int x=idx-1;
        while(y<5 && map[y+1][x] == 0){
            y++;
        }
        map[y][x] = color;
    }

    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    private boolean boom(){
        boolean boom = false;
        Queue<int[]> q= new LinkedList<>();
        Queue<int[]> avail = new LinkedList<>();
        boolean[][] visit = new boolean[6][6];
        for(int y=0;y<6;y++){
            for(int x=0;x<6;x++){
                if(map[y][x] != 0 && !visit[y][x]){
                    visit[y][x]= true;
                    q.add(new int[]{y, x, map[y][x]});
                    avail.add(new int[]{y,x});

                    while(!q.isEmpty()){
                        int[] p =q.poll();
                        int r = p[0];
                        int c= p[1];
                        int color = p[2];
                        for(int i=0;i<4;i++){
                            int ny = r+dy[i];
                            int nx = c+dx[i];
                            if(ny>=0&&nx>=0&&ny<6&&nx<6&&map[ny][nx]==color&&!visit[ny][nx]){
                                visit[ny][nx]= true;
                                q.add(new int[]{ny,nx,color});
                                avail.add(new int[]{ny,nx});
                            }
                        }
                    }
                    if(avail.size() >= 3){
                        boom = true;
                        while(!avail.isEmpty()){
                            int[] ap = avail.poll();
                            int r = ap[0];
                            int c = ap[1];
                            map[r][c]=0;
                        }
                    }else avail.clear();
                }
            }
        }
        return boom;
    }

    private void letdown(){
        for(int y=4;y>=0;y--){
            for(int x=0;x<6;x++){
                if(map[y][x]!=0){
                    int r = y;
                    while(r<5 && map[r][x] != 0 && map[r+1][x] == 0){
                        map[r+1][x] = map[r][x];
                        map[r][x]=0;
                        r++;
                    }
                }
            }
        }
    }
}

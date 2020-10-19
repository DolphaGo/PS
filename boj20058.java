import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map,arr,temp;
    static int[] dy={-1,1,0,0};
    static int[] dx={0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int k=Integer.parseInt(st.nextToken());
        int q=Integer.parseInt(st.nextToken());

        n=pow(2,k);
        map=new int[n][n];
        for(int y=0;y<n;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<n;x++){
                map[y][x]=Integer.parseInt(st.nextToken());
            }
        }

        st=new StringTokenizer(br.readLine());
        for(int i=0;i<q;i++){
            int lv=Integer.parseInt(st.nextToken());
            if(lv>0) {
                int size=pow(2,lv);
                arr=new int[size][size];
                temp=new int[size][size];
                rotate(0,0,n-1,n-1,k,lv);
            }
            melt();
        }

        int sum=0;
        for(int y=0;y<n;y++){
            for(int x=0;x<n;x++){
                sum+=map[y][x];
            }
        }
        System.out.println(sum);
        System.out.println(getBiggest());
    }
    static void rotate(int sy,int sx,int ey,int ex,int k,int lv){
        int my=(sy+ey)>>1;
        int mx=(sx+ex)>>1;
        if(k!=lv){
            rotate(sy,sx,my,mx,k-1,lv); //1
            rotate(sy,mx+1,my,ex,k-1,lv); //2
            rotate(my+1,sx,ey,mx,k-1,lv); //3
            rotate(my+1,mx+1,ey,ex,k-1,lv); //4
            return;
        }

        int l=(ey-sy+1);
        for(int y=sy;y<=ey;y++){
            for(int x=sx;x<=ex;x++){
                arr[y-sy][x-sx]=map[y][x];
            }
        }
        for(int y=0;y<l;y++){
            for(int x=0;x<l;x++){
                temp[x][l-1-y]=arr[y][x];
            }
        }

        for(int y=sy;y<=ey;y++){
            for(int x=sx;x<=ex;x++){
                map[y][x]=temp[y-sy][x-sx];
            }
        }

    }

    static void melt(){
        Queue<int[]> q=new LinkedList<>();
        for(int y=0;y<n;y++){
            for(int x=0;x<n;x++){
                if(map[y][x]>0){
                    int cnt=0;
                    for(int i=0;i<4;i++){
                        int ny=y+dy[i];
                        int nx=x+dx[i];
                        if(isRange(ny,nx)&&map[ny][nx]>0) ++cnt;
                    }
                    if(cnt<3) q.add(new int[]{y,x});
                }
            }
        }
        while(!q.isEmpty()){
            int[] p=q.poll();
            int y=p[0];
            int x=p[1];
            map[y][x]-=1;
        }

    }
    static int getBiggest(){
        int max=0;
        Queue<int[]> q=new LinkedList<>();
        boolean[][] visit=new boolean[n][n];
        for(int y=0;y<n;y++){
            for(int x=0;x<n;x++){
                if(map[y][x]>0&&!visit[y][x]){
                    visit[y][x]=true;
                    int size=1;
                    q.add(new int[]{y,x});
                    while(!q.isEmpty()){
                        int[] p=q.poll();
                        for(int i=0;i<4;i++){
                            int ny=p[0]+dy[i];
                            int nx=p[1]+dx[i];
                            if(isRange(ny,nx)&&map[ny][nx]>0&&!visit[ny][nx]){
                                visit[ny][nx]=true;
                                ++size;
                                q.add(new int[]{ny,nx});
                            }
                        }
                    }
                    max=Math.max(max,size);
                }
            }
        }
        return max;
    }
    static boolean isRange(int y,int x){
        return 0<=y&&y<n&&0<=x&&x<n;
    }
    static int pow(int a,int b){
        return b==0?1:a*pow(a,b-1);
    }
}
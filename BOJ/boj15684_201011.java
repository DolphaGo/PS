import java.io.*;
import java.util.*;

public class Main {
    static int h,w,answer;
    static int[][] map;
    static ArrayList<int[]> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
        h=Integer.parseInt(st.nextToken());

        map=new int[h+1][2*w+1]; // 1 2 3 4 => 1 3 5 7 (2x-1)
        for(int i=0;i<k;i++){
            st=new StringTokenizer(br.readLine());
            int r=Integer.parseInt(st.nextToken());
            int c=Integer.parseInt(st.nextToken());
            map[r][2*c]=1;
        }

        list=new ArrayList<>();
        for(int y=1;y<=h;y++){
            for(int x=1;x<2*w-2;x+=2){
                if(map[y][x-1]==0&&map[y][x+1]==0) list.add(new int[]{y,x});
            }
        }

        answer=-1;
        for(int cnt=0;cnt<=3;cnt++){
            go(0,0,cnt);
            if(answer!=-1) break;
        }
        System.out.println(answer);
    }
    static void go(int v,int c, int cnt){
        if(c==cnt){
            boolean flag=simulation();
            if(flag) answer=cnt;
            return;
        }
        if(v==list.size()) return;
        int[] cur=list.get(v);

        if(map[cur[0]][cur[1]-1]==0){
            map[cur[0]][cur[1]+1]=1;
            go(v+1,c+1,cnt);
            map[cur[0]][cur[1]+1]=0;
        }
        go(v+1,c,cnt);
    }
    static boolean simulation(){
        for(int num=1;num<=2*w-1;num+=2){
            int x=num;
            int y=1;
            while(y<=h){
                if(map[y][x-1]==1) x-=2;
                else if(map[y][x+1]==1) x+=2;
                y++;
            }
            if(x!=num) return false;
        }
        return true;
    }
}
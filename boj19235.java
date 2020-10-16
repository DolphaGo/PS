import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());

        int[][] A=new int[6][4];
        int[][] B=new int[6][4];

        int answer=0;
        for(int i=1;i<=n;i++) {
            st=new StringTokenizer(br.readLine());
            int t=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            int x=Integer.parseInt(st.nextToken());
            switch(t) {
            case 1://1x1
                Garo(A,2,x,1,i);
                Garo(B,2,3-y,1,i);
                break;
            case 2://1x2
                Garo(A,2,x,2,i);
                Sero(B,2,3-y,2,i);
                break;
            case 3://2x1
                Sero(A,2,x,2,i);
                Garo(B,2,2-y,2,i);
                break;
            }

            int val=getScore(A);
            while(val>0) {
                dropDown(A);
                answer+=val;
                val=getScore(A);
            }
            
            val=getScore(B);
            while(val>0) {
                dropDown(B);
                answer+=val;
                val=getScore(B);
            }
            clean(A); clean(B);
        }
        
        System.out.println(answer);
        int count=0;
        for(int y=0;y<6;y++) {
            for(int x=0;x<4;x++) {
                if(A[y][x]>0) ++count;
                if(B[y][x]>0) ++count;
            }
        }
        System.out.println(count);
    }
    static void Garo(int[][] arr,int starty,int startx,int len,int val) {
        int where=6;
        loop:for(int y=starty;y<6;y++) {
            for(int x=startx;x<startx+len;x++) {
                if(arr[y][x]!=0) {
                    where=y;
                    break loop;
                }
            }
        }
        for(int x=startx;x<startx+len;x++) arr[where-1][x]=val;
    }
    static void Sero(int[][] arr,int starty,int startx,int len,int val) {
        int where=6;
        //where인 지점은 못 놓는 곳
        loop:for(int y=starty;y<6;y++) {
            for(int sy=y;sy>y-len;sy--) {
                if(arr[sy][startx]!=0) {
                    where=sy;
                    break loop;
                }
            }
        }
        for(int y=where-1;y>=where-len;y--) arr[y][startx]=val;
    }
    static int getScore(int[][] arr) {
        int score=0;
        for(int y=0;y<6;y++) {
            boolean flag=true;
            for(int x=0;x<4;x++) {
                if(arr[y][x]==0) flag=false;
            }
            if(flag) {
                Arrays.fill(arr[y], 0);
                ++score;
            }
        }
        return score;
    }
    static void dropDown(int[][] arr) {
        for(int y=4;y>=0;y--) {
            for(int x=0;x<4;x++) {
                if(arr[y][x]>0) {
                    int mark=arr[y][x];
                    if(y-1>=0&&arr[y-1][x]==arr[y][x]) {//type 3
                        arr[y-1][x]=arr[y][x]=0;
                        Sero(arr,y,x,2,mark);
                    }else if(x+1<4&&arr[y][x]==arr[y][x+1]) {//type 2
                        arr[y][x]=arr[y][x+1]=0;
                        Garo(arr,y,x,2,mark);
                        x++;
                    }else { // type 1
                        arr[y][x]=0;
                        Sero(arr,y,x,1,mark); //해당 위치에서 놓고
                    }
                }
            }
        }
    }
    static void clean(int[][] arr) {
        // 0,1번 row에 값이 있는지 검사하고 지워주는 로직
        int count=0;
        for(int y=0;y<2;y++) {
            boolean flag=true;
            for(int x=0;x<4;x++) {
                if(arr[y][x]!=0) flag=false;
            }
            if(!flag) ++count;
        }

        if(count>0) {
            for(int y=5-count;y>=0;y--) {
                for(int x=0;x<4;x++) {
                    arr[y+count][x]=arr[y][x];
                }
                Arrays.fill(arr[y], 0);
            }
        }
    }
}
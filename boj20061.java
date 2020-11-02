import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] A=new int[6][4];
        int[][] B=new int[6][4];
        int score=0;
        int n=Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            int t=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            int x=Integer.parseInt(st.nextToken());
            //놓기
            if(t==1){ // 1x1
                move(A,x,1);
                move(B,3-y,1);
            }else if(t==2){ // 1x2
                move(A,x,2);
                move(B,3-y,3);
            }else{ // 2x1
                move(A,x,3);
                move(B,2-y,2);
            }

            //점수 계산
            score+=getScore(A);
            score+=getScore(B);
            letDown(A); letDown(B);
        }
        int count=0;
        for(int y=0;y<6;y++){
            for(int x=0;x<4;x++){
                count+=A[y][x];
                count+=B[y][x];
            }
        }
        System.out.println(score+"\n"+count);
    }
    static void move(int[][] block, int sx,int type){
        int y=0;
        if(type==1){
            while(y<6&&block[y][sx]==0) y++;
            block[y-1][sx]=1;
        }else if(type==2){
            while(y<6) {
                boolean flag=true;
                for (int x = sx; x <= sx + 1; x++) {
                    if(block[y][x]!=0) flag=false;
                }
                if(flag) y++;
                else break;
            }
            for(int x=sx;x<=sx+1;x++){
                block[y-1][x]=1;
            }
        }else{
            while(y<5) {
                boolean flag=true;
                for (int yy = y; yy <= y + 1; yy++) {
                    if(block[yy][sx]!=0) flag=false;
                }
                if(flag) y++;
                else break;
            }
            for(int yy=y-1;yy<=y;yy++){
                block[yy][sx]=1;
            }
        }
    }
    static int getScore(int[][] block){
        int score=0;
        for(int y=0;y<6;y++){
            boolean flag=true;
            for(int x=0;x<4;x++){
                if(block[y][x]==0) flag=false;
            }
            if(flag){
                ++score;
                for(int x=0;x<4;x++){
                    block[y][x]=0;
                }
            }
        }
        return score;
    }
    static void letDown(int[][] block){
        for(int y=4;y>=0;y--){
            int ty=y+1;
            while(ty<6) {
                boolean flag=true;
                for (int x = 0; x < 4; x++) {
                    if (block[ty][x] != 0) flag=false;
                }
                if(flag)++ty;
                else break;
            }
            //y에 있는 것을 ty-1로 옮길 것.
            if(y!=ty-1){
                for(int x=0;x<4;x++){
                    block[ty-1][x]=block[y][x];
                    block[y][x]=0;
                }
            }
        }

        //위의 2줄 검사
        int removeRow=Integer.MAX_VALUE;
        loop:for(int y=0;y<2;y++){
            for(int x=0;x<4;x++){
                if(block[y][x]!=0){
                    //y==0 -> 2 , y==1 -> 1 : 즉 2-y줄 제거
                    removeRow=y;
                    break loop;
                }
            }
        }
        if(removeRow!=Integer.MAX_VALUE) {
            for (int iter = 1; iter <= 2 - removeRow; iter++) {
                for (int y = 4; y >= 0; y--) {
                    for (int x = 0; x < 4; x++) {
                        block[y + 1][x] = block[y][x];
                    }
                }
                for (int x = 0; x < 4; x++) {
                    block[0][x] = 0;
                }
            }
        }
    }
}
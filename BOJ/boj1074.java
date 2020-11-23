import java.io.*;
import java.util.*;

public class Main {
    static int r,c,answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        r=Integer.parseInt(st.nextToken());
        c=Integer.parseInt(st.nextToken());

        int size=1<<n;
        getAnswer(0,0,size,0);
        System.out.println(answer);
    }
    static void getAnswer(int y, int x, int size,int count){
        if(y==r && x==c){
            answer=count;
            return;
        }
        //만약 현재 사각형 내의 범위안에 들어온다면
        if(isRange(y,x, size)){
            int msize = size>>1; //4분할로 쪼갰을 때의 크기
            int num=msize*msize; //한 블럭당 숫자의 개수
            getAnswer(y,x,msize,count); //좌상단
            getAnswer(y,x+msize,msize,count+num); //우상단
            getAnswer(y+msize,x,msize,count+2*num);//좌하단
            getAnswer(y+msize,x+msize,msize,count+3*num);//우하단
        }
    }
    static boolean isRange(int y,int x,int size){
        return y<=r&&r<y+size &&  x<=c&& c<x+size;
    }
}


import java.util.*;
import java.io.*;

public class Main {
    static int[] answer;
    static int[][] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        arr=new int[n][n];
        for(int y=0;y<n;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<n;x++){
                arr[y][x]=Integer.parseInt(st.nextToken());
            }
        }
        answer=new int[2];
        go(0,0,n);
        System.out.printf(answer[0]+"\n"+answer[1]);
    }
    static void go(int sy,int sx,int len){
        int flag=check(sy,sx,len);
        if(flag==-1){
            int nlen=len/2;
            int my=sy+nlen;
            int mx=sx+nlen;
            go(sy,sx,nlen);
            go(sy,mx,nlen);
            go(my,sx,nlen);
            go(my,mx,nlen);
        }else answer[flag]++;
    }
    static int check(int sy,int sx,int len){
        int pivot=arr[sy][sx];
        for(int y=sy;y<sy+len;y++){
            for(int x=sx;x<sx+len;x++){
                if(arr[y][x]!=pivot) return -1;
            }
        }
        return pivot;
    }
}

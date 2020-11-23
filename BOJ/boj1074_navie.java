import java.io.*;
import java.util.*;

public class Main {
    static boolean flag=false;
    static int r,c,count=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        r=Integer.parseInt(st.nextToken());
        c=Integer.parseInt(st.nextToken());

        int size=1<<n;
        divide(0,0,size);
        System.out.println(count);
    }
    static void divide(int sy,int sx,int size){
        if(size>2){
            int ey=sy+size;
            int ex=sx+size;
            int my=(sy+ey)>>1;
            int mx=(sx+ex)>>1;
            divide(sy,sx,size>>1);
            divide(sy,mx,size>>1);
            divide(my,sx,size>>1);
            divide(my,mx,size>>1);
        }else {
            if (flag) return;
            for (int y = sy; y < sy + size; y++) {
                for (int x = sx; x < sx + size; x++) {
                    if (y == r && x == c) {
                        flag = true;
                        return;
                    }
                    ++count;
                }
            }
        }
    }
}


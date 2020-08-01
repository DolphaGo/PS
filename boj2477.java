import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K=Integer.parseInt(br.readLine());

        int[][] arr=new int[6][2];
        int[] dir=new int[5];
        int maxH=0; int maxW=0;
        int hidx=0; int widx=0;

        for(int i=0;i<6;i++){
            st=new StringTokenizer(br.readLine());
            int d=arr[i][0]=Integer.parseInt(st.nextToken());
            arr[i][1]=Integer.parseInt(st.nextToken());
            if(d==3||d==4) {
                if(maxH<arr[i][1]){
                    maxH=arr[i][1];
                    hidx=i;
                }
            }
            else {
                if(maxW<arr[i][1]){
                    maxW=arr[i][1];
                    widx=i;
                }
            }
            dir[d]++;
        }
        int sub,hp,hw;
        if((dir[3]==2&&dir[2]==2)||(dir[4]==2&&dir[1]==2)){
            hp=(hidx+2)%6;
            hw=(widx-2+6)%6;
        }else{
            hp=(hidx-2+6)%6;
            hw=(widx+2)%6;
        }
        sub=arr[hp][1]*arr[hw][1];
        int area=(maxH*maxW)-sub;
        System.out.println(K*area);
    }
}

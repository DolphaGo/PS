import java.io.*;
import java.util.*;

public class Main {
    static int n,k, useless;
    static int[] arr;
    static boolean[] robot;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[2 * n];
        robot = new boolean[2 * n];
        st = new StringTokenizer(br.readLine());
        useless=0;
        for (int i = 0; i < 2 * n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(arr[i]==0) useless++;
        }

        int t = 0;
        while (useless < k) {
            ++t;
            conveyMove(); // 1
            robotMove(); // 2
            upload(); // 3
        }
        System.out.println(t);
    }
    static void conveyMove(){
        int temp=arr[2*n-1];
        for(int i=2*n-1;i>=1;i--) {
            robot[i]=robot[i-1];
            arr[i]=arr[i-1];
        }
        arr[0]=temp;
        robot[0]=false;
    }
    static void robotMove(){
        //컨베이어 벨트로 인해 n에 도착했을 때
        if(robot[n-1]) robot[n-1]=false;

        for(int i=n-2;i>=0;i--){
            if(robot[i]&&arr[i+1]>0&&!robot[i+1]){
                robot[i]=false;
                robot[i+1]=true;
                arr[i+1]-=1; //내구도 감소
                if(arr[i+1]==0) useless++;
            }
        }
        //로봇 이동 후 n에 도착했을 때
        if(robot[n-1]) robot[n-1]=false;
    }
    static void upload(){
        if(arr[0]>0&&!robot[0]) {
            robot[0]=true;
            arr[0]-=1;
            if(arr[0]==0) useless++;
        }
    }
}
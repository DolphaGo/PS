import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr=new int[9];
        for(int i=0;i<9;i++){
            arr[i]=Integer.parseInt(br.readLine());
        }

        for(int i=(1<<7)-1;i<(1<<9);i++){
            int cnt=0;
            int sum=0;
            for(int j=0;j<9;j++){
                if((i&(1<<j))>0) {
                    sum+=arr[j];
                    ++cnt;
                }
            }

            if(cnt==7&&sum==100){
                for(int j=0;j<9;j++){
                    if((i&(1<<j))>0){
                        System.out.println(arr[j]);
                    }
                }
                break;
            }
        }
    }
}
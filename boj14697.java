import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        int[] nums=new int[3];
        for(int i=0;i<3;i++) nums[i]=Integer.parseInt(st.nextToken());
        int people=Integer.parseInt(st.nextToken());

        int[] dp=new int[people+1];
        Arrays.fill(dp,Integer.MAX_VALUE/2);
        for(int i=0;i<3;i++){
            int num=nums[i];
            if(num<=people) dp[num]=1;
            for(int j=0;j<=people;j++){
                if(j>=num && dp[j]>dp[j-num]+1){
                    dp[j]=dp[j-num]+1;
                }
            }
        }
        if(dp[people]==Integer.MAX_VALUE/2) System.out.println(0);
        else System.out.println(1);
    }
}
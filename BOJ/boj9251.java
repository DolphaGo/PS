import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] s1=br.readLine().toCharArray();
        char[] s2=br.readLine().toCharArray();

        System.out.println(LCS(s1,s2));
    }
    static int LCS(char[] s1,char[] s2){
        int h=s1.length;
        int w=s2.length;
        int[][] dp=new int[h+1][w+1];

        for(int i=1;i<=h;i++){
            for(int j=1;j<=w;j++){
                if(s1[i-1]==s2[j-1])
                    dp[i][j]=dp[i-1][j-1]+1;
                else
                    dp[i][j]=Math.max(dp[i][j-1],dp[i-1][j]);
            }
        }
        return dp[h][w];
    }
}
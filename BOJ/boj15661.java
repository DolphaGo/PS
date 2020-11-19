import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n=Integer.parseInt(br.readLine());
        arr=new int[n][n];
        for(int y=0;y<n;y++){
            st=new StringTokenizer(br.readLine());
            for(int x=0;x<n;x++){
                arr[y][x]=Integer.parseInt(st.nextToken());
            }
        }

        List<Integer> a=new ArrayList<>();
        List<Integer> b=new ArrayList<>();
        int answer=Integer.MAX_VALUE;
        //00111과 1000은 동일하다.
        for(int i=1;i<(1<<n-1);i++){
            for(int j=0;j<n;j++){
                if((i&(1<<j))>0) a.add(j);
                else b.add(j);
            }

            int aScore=getScore(a);
            int bScore=getScore(b);

            answer=Math.min(answer,Math.abs(aScore-bScore));
            a.clear();  b.clear();
        }
        System.out.println(answer);
    }
    static int getScore(List<Integer> list){
        int score=0;
        for(int i=0;i<list.size();i++){
            int cur=list.get(i);
            for(int j=i+1;j<list.size();j++){
                int next=list.get(j);
                score+=arr[cur][next]+arr[next][cur];
            }
        }
        return score;
    }
}
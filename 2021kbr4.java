import java.util.*;

public class Main4 {
    public static void main(String[] args) {
        int n=6;
        int s=4;
        int a=6;
        int b=2;
        int[][] fares=new int[][]{
                {4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}
        };

        int[][] dist=new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            Arrays.fill(dist[i],Integer.MAX_VALUE/3);
            dist[i][i]=0;
        }

        for(int i=0;i<fares.length;i++){
            int[] fare=fares[i];
            int x=fare[0];
            int y=fare[1];
            int c=fare[2];
            dist[x][y]=c;
            dist[y][x]=c;
        }

        //플로이드 워셜
        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(dist[i][j]>dist[i][k]+dist[k][j]){
                        dist[i][j]=dist[i][k]+dist[k][j];
                    }
                }
            }
        }

        //s에서 시작해서 다른 곳을 거치고 각자 집가는 방법
        int answer=Integer.MAX_VALUE;
        for(int i=1;i<=n;i++){
            int cost=dist[s][i]+dist[i][a]+dist[i][b];
            answer=Math.min(answer,cost);
        }

        System.out.println(answer);
    }
}
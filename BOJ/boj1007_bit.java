import java.io.*;
import java.util.*;

public class Main {
    static class Info{
        int from;
        int to;
        double dis;
        Info(int from,int to,double dis){
            this.from=from;
            this.to=to;
            this.dis=dis;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        PriorityQueue<Info> q=new PriorityQueue<>(new Comparator<Info>(){
            public int compare(Info o1,Info o2){
                return Double.compare(o1.dis,o2.dis);
            }
        });
        int T=Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++){
            int n=Integer.parseInt(br.readLine());
            int[][] arr=new int[n][2];
            for(int i=0;i<n;i++){
                st=new StringTokenizer(br.readLine());
                arr[i][0]=Integer.parseInt(st.nextToken());
                arr[i][1]=Integer.parseInt(st.nextToken());
            }

            double answer=Integer.MAX_VALUE;
            for(int i=(1<<n/2)-1;i<(1<<n);i++){
                int cnt=0;
                for(int j=0;j<n;j++){
                    if((i&(1<<j))>0) ++cnt;
                }
                if(cnt!=n/2) continue;

                int[] A=new int[2];
                int[] B=new int[2];

                for(int j=0;j<n;j++){
                    if((i&(1<<j))>0){
                        A[0]+=arr[j][0];
                        A[1]+=arr[j][1];
                    }else{
                        B[0]+=arr[j][0];
                        B[1]+=arr[j][1];
                    }
                }
                double dis=getdis(A,B);
                answer=Math.min(answer,dis);
            }
            sb.append(answer+"\n");
        }
        System.out.print(sb);
    }
    static private double getdis(int[] A,int[] B){
        int ydiff=A[0]-B[0];
        int xdiff=A[1]-B[1];
        return Math.sqrt(Math.pow(ydiff,2)+Math.pow(xdiff,2));
    }
}
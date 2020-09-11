import java.io.*;
import java.util.*;
public class Main{
    static int n;
    static int find(int a){
        if(par[a]==a) return a;
        return par[a]=find(par[a]);
    }
    static int[][] data;
    static int[] par;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb=new StringBuilder();
        int T=Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++){
            n=Integer.parseInt(br.readLine());
            par=new int[n+1];
            for(int i=1;i<=n;i++) par[i]=i;
            data=new int[n+1][3];
            for(int i=1;i<=n;i++){
                st=new StringTokenizer(br.readLine());
                data[i][0]=Integer.parseInt(st.nextToken());
                data[i][1]=Integer.parseInt(st.nextToken());
                data[i][2]=Integer.parseInt(st.nextToken());
            }
            sb.append(solve()).append("\n");
        }
        System.out.print(sb.toString());
    }
    static int solve(){
        int bind=0;
        for(int i=1;i<=n;i++){
            int[] a=data[i];
            for(int j=i+1;j<=n;j++){
                int[] b=data[j];
                double dis=getDis(a,b);
                if(dis<=a[2]+b[2]){
                    int x=find(i);
                    int y=find(j);
                    if(x!=y){
                        if(x>y) par[x]=y;
                        else par[y]=x;
                        ++bind;
                    }
                }
            }
        }
        return n-bind;
    }
    static double getDis(int[] a,int[] b){
        return Math.sqrt(Math.pow(a[0]-b[0],2)+Math.pow(a[1]-b[1],2));
    }
}
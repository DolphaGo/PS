import java.util.*;
import java.io.*;

public class Main {
    static int n,c,lo,hi;
    static int[] arr,hat;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        c=Integer.parseInt(st.nextToken());

        arr =new int[n+1];
        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) arr[i]=Integer.parseInt(st.nextToken());

        int m=Integer.parseInt(br.readLine());
        int[][] query=new int[m][3];

        StringBuilder sb=new StringBuilder();
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            query[i][0]=a;
            query[i][1]=b;
            query[i][2]=i;
        }

        Arrays.sort(query, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int sqrt=(int)Math.sqrt(n);
                int x=o1[1]/sqrt;
                int y=o2[1]/sqrt;
                if(x==y) return Integer.compare(o1[0],o2[0]);
                else return Integer.compare(x,y);
            }
        });

        hat=new int[10001];
        lo=1; hi=0;
        String[] answer=new String[m];
        for(int i=0;i<m;i++){
            int[] q=query[i];
            int X=query(q[0],q[1]);
            if(X==0) answer[q[2]]="no";
            else answer[q[2]]="yes "+X;
        }
        for(int i=0;i<m;i++){
            sb.append(answer[i]).append("\n");
        }
        System.out.print(sb);
    }
    static int query(int a,int b){
        int len=b-a+1;
        while(lo<a) --hat[arr[lo++]];
        while(lo>a) ++hat[arr[--lo]];
        while(hi<b) ++hat[arr[++hi]];
        while(hi>b) --hat[arr[hi--]];
        for(int i=1;i<=c;i++){
            if(hat[i]>len/2) return i;
        }
        return 0;
    }
}
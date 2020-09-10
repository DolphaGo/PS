import java.util.*;
import java.io.*;

public class Main {
    static final int MAX=100001;
    static int n,m,max,lo,hi;
    static int[] arr,count, table;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n=Integer.parseInt(br.readLine());
        arr=new int[n+1];
        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++) arr[i]=Integer.parseInt(st.nextToken());

        m=Integer.parseInt(br.readLine());
        int[][] query=new int[m][3];
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            query[i][0]=Integer.parseInt(st.nextToken());
            query[i][1]=Integer.parseInt(st.nextToken());
            query[i][2]=i;
        }

        Arrays.sort(query,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                int sqrt=(int)Math.sqrt(n);
                int x=o1[1]/sqrt;
                int y=o2[1]/sqrt;
                if(x==y) return Integer.compare(o1[0],o2[0]);
                else return Integer.compare(x,y);
            }
        });

        lo=1; hi=0; max=0;
        count=new int[MAX];//각 숫자가 몇번 등장했는지
        table =new int[MAX]; //개수가 i인 수가 몇개인지
        int[] answer =new int[m];
        for(int i=0;i<m;i++){
            int[] q=query[i];
            while(lo>q[0]) add(--lo);
            while(hi<q[1]) add(++hi);
            while(lo<q[0]) rm(lo++);
            while(hi>q[1]) rm(hi--);
            answer[q[2]]=max;
        }

        StringBuilder sb=new StringBuilder();
        for(int i=0;i<m;i++) sb.append(answer[i]).append("\n");
        System.out.print(sb);
    }
    static void add(int idx){
        if(count[arr[idx]]==max) ++max;
        --table[count[arr[idx]]];
        ++table[++count[arr[idx]]];
    }
    static void rm(int idx){
        if(count[arr[idx]]==max && table[count[arr[idx]]]==1)  --max;
        --table[count[arr[idx]]];
        ++table[--count[arr[idx]]];
    }
}
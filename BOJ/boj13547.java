import java.io.*;
import java.util.*;

public class Main {
    static int cnt;
    static int[] arr;
    static int[] count=new int[1000001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        arr=new int[n];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) arr[i]=Integer.parseInt(st.nextToken());

        int sqrtN=(int)Math.sqrt(n);

        ArrayList<int[]> qlist=new ArrayList<>();
        int m=Integer.parseInt(br.readLine());
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken())-1;
            int b=Integer.parseInt(st.nextToken())-1;
            qlist.add(new int[]{a,b,i});
        }
        Collections.sort(qlist,new Comparator<int[]>(){
            public int compare(int[] o1,int[] o2){
                int o1r=o1[1]/sqrtN;
                int o2r=o2[1]/sqrtN;
                if(o1r==o2r) return Integer.compare(o1[0],o2[0]);
                return Integer.compare(o1r,o2r);
            }
        });

        int[] answer=new int[m];
        int lo=0, hi=0;
        add(lo);

        for(int i=0;i<qlist.size();i++){
            int[] q= qlist.get(i);
            while(lo>q[0]) add(--lo);
            while(hi<q[1]) add(++hi);
            while(lo<q[0]) remove(lo++);
            while(hi>q[1]) remove(hi--);
            answer[q[2]]= cnt;
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<m;i++) sb.append(answer[i]).append("\n");
        System.out.print(sb);
    }
    static void add(int idx){
        if(count[arr[idx]]++ == 0) ++cnt;
    }

    static void remove(int idx){
        if(--count[arr[idx]] == 0) --cnt;
    }
}
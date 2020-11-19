import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int C=Integer.parseInt(st.nextToken());
        int N=Integer.parseInt(st.nextToken());

        TreeMap<Integer,Integer> map=new TreeMap<>();
        for(int i=0;i<C;i++){
            int t=Integer.parseInt(br.readLine());
            if(map.containsKey(t)) map.replace(t,map.get(t)+1);
            else map.put(t,1);
        }

        int[][] cows=new int[N][2];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            cows[i][0]=Integer.parseInt(st.nextToken());
            cows[i][1]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cows, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]==o2[1]) return Integer.compare(o2[0],o1[0]);
                else return Integer.compare(o1[1],o2[1]);
            }
        });

        int answer=0;
        for(int i=0;i<N;i++){
            int[] cow=cows[i];
            int A=cow[0];
            int B=cow[1];

            Map.Entry<Integer,Integer> x=map.ceilingEntry(A);
            if(x==null) continue;;

            int T=x.getKey();
            int cnt=x.getValue();
            if(T<=B){
                ++answer;
                --cnt;
                if(cnt==0) map.remove(T);
                else map.replace(T,cnt);
            }
        }
        System.out.println(answer);
    }
}
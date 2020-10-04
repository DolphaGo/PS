import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0],o2[0]);
            }
        });
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            int arrive=Integer.parseInt(st.nextToken());
            int delay=Integer.parseInt(st.nextToken());
            pq.add(new int[]{arrive,delay});
        }

        int time=0;
        while(!pq.isEmpty()){
            int[] p=pq.poll();
            if(time<=p[0]) time=p[0]+p[1];
            else time+=p[1];
        }
        System.out.println(time);
    }
}
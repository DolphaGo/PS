import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int C=Integer.parseInt(st.nextToken());
        int L=Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]==o2[1]) return Integer.compare(o1[1]-o1[0],o2[1]-o2[0]);
                else return Integer.compare(o1[1],o2[1]);
            }
        });

        for(int i=0;i<C;i++){
            st=new StringTokenizer(br.readLine());
            int l=Integer.parseInt(st.nextToken());
            int r=Integer.parseInt(st.nextToken());
            pq.add(new int[]{l,r});
        }

        TreeMap<Integer,Integer> map=new TreeMap<>();
        for(int i=0;i<L;i++){
            st=new StringTokenizer(br.readLine());
            int spf=Integer.parseInt(st.nextToken());
            int cnt=Integer.parseInt(st.nextToken());
            if(map.containsKey(spf)) map.replace(spf,map.get(spf)+cnt);
            else map.put(spf,cnt);
        }

        int answer=0;
        while(!pq.isEmpty()&&map.size()>0){
            int[] p=pq.poll();
            int l=p[0];

            Map.Entry<Integer,Integer> x=map.ceilingEntry(l);
            if(x==null) continue;
            int spf=x.getKey();
            int cnt=x.getValue();
            if(spf<=p[1]){
                ++answer;
                --cnt;
                if(cnt==0) map.remove(spf);
                else map.replace(spf,cnt);
            }
        }
        System.out.println(answer);
    }
}
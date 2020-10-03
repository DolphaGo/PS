import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());


        //구매할 수 있는 최대 가격이 낮은순으로, 최소 가격은 높은 순으로 정렬 : (구매할 수 있는 범위가 좁은 순으로 오름차순)
        PriorityQueue<long[]> pq=new PriorityQueue<>(new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                if(o1[1]==o2[1]) return Long.compare(o2[0],o1[0]);
                else return Long.compare(o1[1],o2[1]);
            }
        });;


        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            long l=Long.parseLong(st.nextToken());
            long r=Long.parseLong(st.nextToken());
            pq.offer(new long[]{l,r});
        }


        TreeMap<Long,Integer> mask=new TreeMap<>();
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            long price=Long.parseLong(st.nextToken());
            int count=Integer.parseInt(st.nextToken());
            if(mask.containsKey(price)) mask.replace(price,mask.get(price)+count);
            else mask.put(price,count);
        }

        int answer=0;
        while(!pq.isEmpty()&&!mask.isEmpty()){
            long[] p=pq.poll();
            long l=p[0];
            long r=p[1];

            //lowerbound
            Map.Entry<Long,Integer> x=mask.ceilingEntry(l); // 최저 가격을 기준으로 탐색한다.
            if(x==null) continue;

            if(x.getKey()<=r){
                int count=x.getValue();
                ++answer;
                --count;
                if(count==0) mask.remove(x.getKey());
                else mask.replace(x.getKey(), count);
            }
        }
        System.out.println(answer);
    }
}
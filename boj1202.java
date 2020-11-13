import java.io.*;
import java.util.*;

class Main {
    static TreeMap<Long,Integer> sack;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
        PriorityQueue<long[]> germ=new PriorityQueue<>(new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                return Long.compare(o2[1],o1[1]);
            }//가치가 높은 순으로 정렬
        });

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            long m=Long.parseLong(st.nextToken());
            long v=Long.parseLong(st.nextToken());
            germ.add(new long[]{m,v});
        }
        sack=new TreeMap<>();
        for(int i=0;i<k;i++){
            long s=Long.parseLong(br.readLine());
            if(!sack.containsKey(s)) sack.put(s,1);
            else sack.put(s,sack.get(s)+1);
        }

        long answer=0;
        while(!germ.isEmpty()){
            long[] p=germ.poll();
            Map.Entry<Long,Integer> entry=sack.ceilingEntry(p[0]);
            if(entry==null) continue;
            if(entry.getValue()==1) sack.remove(entry.getKey());
            else sack.put(entry.getKey(),entry.getValue()-1);
            answer+=p[1];
        }
        System.out.println(answer);
    }
}
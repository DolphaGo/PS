import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> map=new HashMap<>();
        int n=Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            String name= br.readLine();
            map.put(name,map.getOrDefault(name,0)+1);
        }

        PriorityQueue<String> pq=new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(map.get(o1).equals(map.get(o2))) return o1.compareTo(o2);
                return Integer.compare(map.get(o2),map.get(o1));
            }
        });

        for(String key : map.keySet()) pq.add(key);
        System.out.println(pq.poll());
    }
}

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> q=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]) return Integer.compare(o2[1],o1[1]);
                return Integer.compare(o1[0],o2[0]);
            }
        });

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int l= Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            q.add(new int[]{l,h});
            q.add(new int[]{r,-h});
        }

        StringBuilder sb=new StringBuilder();
        TreeMap<Integer,Integer> map=new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2,o1);
            }
        });

        int maxX=0;
        int maxH=Integer.MIN_VALUE;
        map.put(0,1);
        while(!q.isEmpty()){
            int[] p=q.poll();
            System.out.println(Arrays.toString(p));
            if(p[1]>0){ //건물의 시작지점
                map.put(p[1],map.getOrDefault(p[1],0)+1);
            }else{ //건물의 끝지점
                int key= -p[1];
                int value=map.get(key);
                if(value==1) map.remove(key);
                else map.replace(key,value-1);
            }

            int h=map.firstKey();
            if(maxX!=p[0]&&maxH!=h){
                sb.append(p[0]).append(" ").append(h).append(" ");
                maxX=p[0];
                maxH=h;
            }
        }
        System.out.print(sb.toString().trim());
    }
}


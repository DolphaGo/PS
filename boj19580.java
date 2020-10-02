import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        PriorityQueue<long[]> pq=new PriorityQueue<>(new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                if(o1[1]==o2[1]) return Long.compare(o2[0],o1[0]);
                else return Long.compare(o2[1],o1[1]);
            }
        });

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            long l=Long.parseLong(st.nextToken());
            long r=Long.parseLong(st.nextToken());
            pq.add(new long[]{l,r});
        }

        long[][] mask=new long[m][2];
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            mask[i][0]=Long.parseLong(st.nextToken());
            mask[i][1]=Long.parseLong(st.nextToken());
        }

        Arrays.sort(mask, new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                return Long.compare(o2[0],o1[0]);
            }
        });

        int answer=0;
        for(int i=0;i<n;i++){
            long x=mask[i][0];
            long cnt=mask[i][1];
            System.out.println("현재 마스크 가격:"+x);
            while(cnt>0 && !pq.isEmpty()){
                System.out.println("남은 개수 :"+cnt);
                long l=pq.peek()[0];
                long r=pq.peek()[1];
                if(r<x) break;
                if(l<=x){
                    System.out.println(l+"~"+r+"을 가진애한테 팔게");
                    ++answer;
                    --cnt;
                }
                pq.poll();
            }
        }
        System.out.println(answer);
    }
}
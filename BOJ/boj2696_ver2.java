import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        StringBuilder sb=new StringBuilder();
        PriorityQueue<Integer> minHeap =new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap =new PriorityQueue<>(Collections.reverseOrder());

        int T=Integer.parseInt(br.readLine());
        for(int tc=1;tc<=T;tc++){
            int n =Integer.parseInt(br.readLine());
            int cnt = 0; //출력용
            sb.append((n +1)/2).append("\n");

            for(int i=0;i<n;i++) {
                if (i % 10 == 0) st = new StringTokenizer(br.readLine());
                int val = Integer.parseInt(st.nextToken());
                if (minHeap.size() == maxHeap.size()) maxHeap.add(val);
                else minHeap.add(val);

                //maxHeap은 중앙값 이하의 숫자만 갖도록
                if (!minHeap.isEmpty()) {
                    if (maxHeap.peek() > minHeap.peek()) {
                        int p1 = maxHeap.poll();
                        int p2 = minHeap.poll();
                        maxHeap.add(p2);
                        minHeap.add(p1);
                    }
                }

                if (i % 2 == 0) {
                    sb.append(maxHeap.peek()).append(" ");
                    if(++cnt % 10 == 0) sb.append("\n");
                }
            }
            sb.append("\n");
            minHeap.clear();
            maxHeap.clear();
        }
        System.out.print(sb);
    }
}
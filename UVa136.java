import java.io.*;
import java.util.*;

class Main {
    static int[] mul={2,3,5};
    static long[] dp=new long[1501];
    static class Node{
        long val;
        int prev;
        Node(long val,int prev){
            this.val=val;
            this.prev=prev;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        setData();
        StringBuilder sb=new StringBuilder();
        int n;
        while((n=Integer.parseInt(br.readLine()))!=0){
            sb.append(dp[n]).append("\n");
        }
        System.out.print(sb);
    }
    static void setData(){
        int idx=0;
        PriorityQueue<Node> pq=new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Long.compare(o1.val,o2.val);
            }
        });
        pq.add(new Node(1,0));
        while(!pq.isEmpty()&&idx<1500){
            Node p=pq.poll();
            dp[++idx]=p.val;
            for(int i=p.prev;i<3;i++){
                long next=p.val*mul[i];
                pq.add(new Node(next,i));
            }
        }
    }

}
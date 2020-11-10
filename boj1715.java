import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        PriorityQueue<Integer> q=new PriorityQueue<>();
        for(int i=0;i<n;i++){
            q.add(Integer.parseInt(br.readLine()));
        }
        int answer=0;
        while(q.size()>1){ //2개 이상일 때
            int a=q.poll();
            int b=q.poll();
            int c=a+b;
            answer+=c;
            q.add(c);
        }
        System.out.println(answer);
    }
}
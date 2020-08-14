import java.io.*;
import java.util.*;

public class Main {
    static int answer,n;
    static boolean[] visit;
    static String[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s=br.readLine();

        Queue<String> q=new LinkedList<>();
        StringTokenizer st=new StringTokenizer(s,"+|-");
        while(st.hasMoreTokens()){
            q.add(st.nextToken());
        }

        Queue<String> temp=new LinkedList<>();
        //식을 만듬
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='-' || c=='+') {
                temp.add(q.poll());
                temp.add(c+"");
            }
        }
        temp.add(q.poll());
        n=temp.size();
        answer=Integer.MAX_VALUE;
        visit=new boolean[n];
        arr=new String[n];
        int idx=0;
        while(!temp.isEmpty()) arr[idx++]=temp.poll();
        go(1);
        System.out.println(answer);
    }
    static void go(int v){
        if(v>=n){
            Queue<Integer> q=new LinkedList<>();
            for(int i=0;i<n;i+=2){
                if(visit[i]){
                    int prev=Integer.parseInt(arr[i]);
                    int next=Integer.parseInt(arr[i+2]);
                    if(arr[i+1].equals("+")){
                        prev+=next;
                    }else{
                        prev-=next;
                    }
                    q.add(prev);
                    i+=2;
                }else q.add(Integer.parseInt(arr[i]));
            }

            int val=q.poll();
            for(int i=1;i<n;i+=2){
                if(!visit[i]){
                    if(arr[i].equals("+")){
                        val+=q.poll();
                    }else{
                        val-=q.poll();
                    }
                }
            }
            answer=answer>val?val:answer;
            return;
        }

        if(!visit[v-1]&&!visit[v+1]){
            for(int i=-1;i<=1;i++){
                visit[v+i]=true;
            }
            go(v+4);
            for(int i=-1;i<=1;i++){
                visit[v+i]=false;
            }
        }
        go(v+2);
    }
}

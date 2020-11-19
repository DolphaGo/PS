import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        st=new StringTokenizer(br.readLine());
        int prev=Integer.parseInt(st.nextToken());
        int start=prev;
        int answer=0;
        for(int i=1;i<n;i++){
            int val=Integer.parseInt(st.nextToken());
            if(prev>=val){
                answer=Math.max(answer,prev-start);
                start=val;
            }
            prev=val;
        }
        if(prev!=start) answer=Math.max(answer,prev-start);
        System.out.println(answer);
    }
}
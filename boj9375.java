import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        while(T-->0){
            int n=Integer.parseInt(br.readLine());
            Map<String, Integer> map=new HashMap<>();
            for(int i=0;i<n;i++){
                st=new StringTokenizer(br.readLine());
                st.nextToken();
                String category=st.nextToken();
                int count=map.getOrDefault(category,0);
                map.put(category,count+1);
            }
            int answer=1;
            for(String key:map.keySet()){
                answer*=(map.get(key)+1);
            }
            sb.append(answer-1).append("\n");
        }
        System.out.print(sb);
    }
}
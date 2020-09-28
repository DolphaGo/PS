import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int m=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        Map<Integer,Integer> map=new HashMap<>();

        long xor=0;
        long sum=0;
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int q=Integer.parseInt(st.nextToken());
            switch (q){
                case 1:
                    int x=Integer.parseInt(st.nextToken());
                    if(map.containsKey(x)) map.replace(x,map.get(x)+1);
                    else map.put(x,1);
                    sum+=x;
                    xor^=x;
                    break;
                case 2:
                    int y=Integer.parseInt(st.nextToken());
                    int count=map.get(y);
                    if(count==1) map.remove(y);
                    else map.replace(y,count-1);
                    sum-=y;
                    xor^=y;
                    break;
                case 3:
                    sb.append(sum).append("\n");
                    break;
                case 4:
                    sb.append(xor).append("\n");
                    break;
            }
        }
        System.out.print(sb);
    }
}
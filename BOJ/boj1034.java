import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int h=Integer.parseInt(st.nextToken());
        int w=Integer.parseInt(st.nextToken());

        Map<String,Integer> map=new HashMap<>();
        for(int y=0;y<h;y++){
            String s=br.readLine();
            if(map.containsKey(s)) map.replace(s,map.get(s)+1);
            else map.put(s,1);
        }

        int k=Integer.parseInt(br.readLine());
        int answer=0;
        for(String ramp:map.keySet()){
            int zero=0;
            for(int x=0;x<w;x++){
                if(ramp.charAt(x)=='0') ++zero;
            }

            if(k>=zero && (k-zero)%2 == 0){
                answer=Math.max(answer,map.get(ramp));
            }
        }
        System.out.println(answer);
    }
}
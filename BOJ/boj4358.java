import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;

        int total=0;
        TreeMap<String,Integer> map=new TreeMap<>();
        while((s=br.readLine())!=null){
            ++total;
            if(map.get(s)==null) map.put(s,1);
            else map.replace(s,map.get(s)+1);
        }
        StringBuilder sb=new StringBuilder();
        for(String key:map.keySet()){
            double ratio=100.0*map.get(key)/total;
            sb.append(String.format("%s %.4f",key,ratio)).append("\n");
        }
        System.out.print(sb);
    }
}
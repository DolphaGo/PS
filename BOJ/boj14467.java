import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        HashMap<Integer,Integer> map=new HashMap<>();
        int answer=0;
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            int num=Integer.parseInt(st.nextToken());
            int loc=Integer.parseInt(st.nextToken());
            if(!map.containsKey(num)) map.put(num,loc);
            else if(map.get(num)!=loc){
                map.replace(num,loc);
                ++answer;
            }
        }
        System.out.println(answer);
    }
}
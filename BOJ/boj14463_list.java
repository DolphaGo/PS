import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());

        List<Integer> list=new ArrayList<>();
        boolean[] check=new boolean[n+1];
        int answer=0;
        for(int i=0;i<2*n;i++){
            int cow=Integer.parseInt(br.readLine());
            if(!check[cow]){
                check[cow]=true;
                list.add(cow);
            }
            else{
                int idx=list.indexOf(cow);
                answer+=list.size()-1-idx;
                list.remove(idx);
            }
        }
        System.out.println(answer);
    }
}
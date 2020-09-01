import java.io.*;
import java.util.*;

public class Main {
    static final int PRIMES_RANGE=1500000;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());

        ArrayList<Integer> list=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        boolean[] primes=new boolean[PRIMES_RANGE];
        for(int i=2;i<primes.length;i++){
            //소수 확정&팰린드롬 검사
            if(primes[i]) continue;
            String src=i+"";
            sb.append(src);
            sb.reverse();
            if(src.equals(sb.toString())) list.add(i);
            sb.setLength(0);
            //소수가 아닌 것들 제거
            for(int j=2*i;j< primes.length;j+=i){
                primes[j]=true;
            }
        }
        int s=0;
        int e=list.size()-1;
        while(s<e){
            int m=(s+e)>>1;
            if(list.get(m)<n) s=m+1;
            else e=m;
        }
        System.out.println(list.get(e));
    }
}
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        long S=Long.parseLong(br.readLine());
        long s=1;
        long e=100000;
        while(s<e){
            long m=(s+e)>>1;
            long val=m*(m+1)/2;
            if(val<=S) s=m+1;
            else e=m;
        }
        System.out.println(e-1);
    }
}

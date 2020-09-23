import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        long a=Long.parseLong(st.nextToken());
        long b=Long.parseLong(st.nextToken());
        long c=Long.parseLong(st.nextToken());

        System.out.println(multi(a,b,c)%c);
    }
    static long multi(long a,long b,long c){
        if(b==0) return 1L;
        long m=b>>1;
        if(b%2==1) return (a * multi(a,b-1,c))%c;
        else{
            long val=multi(a,m,c)%c;
            return (val*val)%c;
        }
    }
}
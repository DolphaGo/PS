import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n=Long.parseLong(br.readLine());
        long k=Long.parseLong(br.readLine());
        long s=1;
        long e=Math.min(1000000000,n*n);
        while(s<e){
            long m=(s+e)>>1;
            int res=test(n,m);
            if(res<k) s=m+1;
            else e=m ;
        }
        System.out.println(e);
    }
    static int test(long n,long m){
        int cnt=0;
        for(int i=1;i<=n;i++){
            cnt+= Math.min(m / i, n);
        }
        return cnt;
    }
}
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        long a=Long.parseLong(st.nextToken()+st.nextToken());
        long b=Long.parseLong(st.nextToken()+st.nextToken());
        System.out.println(a+b);
    }
}
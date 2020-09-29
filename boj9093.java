import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T=Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        for(int tc=1;tc<=T;tc++){
            st=new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()){
                String s=st.nextToken();
                for(int i=s.length()-1;i>=0;i--) sb.append(s.charAt(i));
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
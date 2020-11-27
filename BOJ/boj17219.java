import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        Map<String,String> map=new HashMap<>();
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            String site=st.nextToken();
            String pass=st.nextToken();
            map.put(site,pass);
        }

        StringBuilder sb=new StringBuilder();
        for(int i=0;i<m;i++){
            String site=br.readLine();
            sb.append(map.get(site)).append("\n");
        }
        System.out.print(sb);
    }
}

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n=Integer.parseInt(br.readLine());
        String[] s=new String[n];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            s[i]=st.nextToken();
        }

        Arrays.sort(s, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String a=o1+o2;
                String b=o2+o1;
                return b.compareTo(a);
            }
        });

        if(s[0].startsWith("0")) System.out.println(0);
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) sb.append(s[i]);
            System.out.print(sb);
        }
    }
}
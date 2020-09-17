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
               int idx1=0;
               int idx2=0;
               while(o1.charAt(idx1)==o2.charAt(idx2)){
                   if(idx1+1<o1.length()) idx1++;
                   if(idx2+1<o2.length()) idx2++;
                   if(idx1==o1.length()-1 || idx2==o2.length()-1) break;
               }
                char c1=o1.charAt(idx1);
                char c2=o2.charAt(idx2);
                if(c1!=c2) return Character.compare(c2, c1);
                else return Integer.compare(o1.length(),o2.length());
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
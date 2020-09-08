import java.io.*;
import java.util.*;

public class Main2 {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        String[] arr=new String[n];
        for(int i=0;i<n;i++){
            arr[i]=br.readLine();
        }
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int len1=o1.length();
                int len2=o2.length();
                int sum1=0;
                int sum2=0;
                for(int i=0;i<o1.length();i++){
                    if('0'<=o1.charAt(i)&&o1.charAt(i)<='9') sum1+=o1.charAt(i)-'0';
                }
                for(int i=0;i<o2.length();i++){
                    if('0'<=o2.charAt(i)&&o2.charAt(i)<='9') sum2+=o2.charAt(i)-'0';
                }
                if(len1!=len2) return Integer.compare(len1,len2);
                else if(sum1!=sum2) return Integer.compare(sum1,sum2);
                else return o1.compareTo(o2);
            }
        });

        StringBuilder sb=new StringBuilder();
        for(String s:arr){
            sb.append(s).append("\n");
        }
        System.out.print(sb);
    }
}
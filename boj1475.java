import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s=br.readLine();
        int[] count=new int[10];
        for(int i=0;i<s.length();i++){
            count[s.charAt(i)-'0']++;
        }
        int sum=count[6]+count[9];
        int max=(int)Math.ceil(1.0*sum/2);
        for(int i=0;i<=9;i++){
            if(i==6 || i==9) continue;
            max=Math.max(max,count[i]);
        }
        System.out.println(max);
    }
}
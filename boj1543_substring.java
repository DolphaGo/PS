import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String src=br.readLine();
        String pattern=br.readLine();

        int answer=0;
        for(int i=0;i<=src.length()-pattern.length();){
            String substring=src.substring(i,i+pattern.length());
            if(substring.equals(pattern)){
                ++answer;
                i+=pattern.length();
            }else i++;
        }
        System.out.println(answer);
    }
}

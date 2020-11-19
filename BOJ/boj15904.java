import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String s=br.readLine();
        char[] seek=new char[]{'U','C','P','C'};
        int idx=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==seek[idx]) idx++;
            if(idx==4) break;
        }
        if(idx==4) System.out.println("I love UCPC");
        else System.out.println("I hate UCPC");
    }
}
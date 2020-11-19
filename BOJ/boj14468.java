import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int answer=0;
        int[][] alpha=new int[26][2];
        char[] arr=br.readLine().toCharArray();
        for(int i=0;i<arr.length;i++){
            int idx=arr[i]-'A';
            if(alpha[idx][0]==0) alpha[idx][0]=i+1;
            else alpha[idx][1]=i+1;
        }

        //겹치는 구간의 수를 세줍니다.
        for(int i=0;i<26;i++){
            for(int j=0;j<26;j++){
                if(alpha[i][0]<alpha[j][0]&&alpha[j][0]<alpha[i][1]&&alpha[i][1]<alpha[j][1]) ++answer;
            }
        }

        System.out.println(answer);
    }
}
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] src=br.readLine().toCharArray();
        char[] pattern=br.readLine().toCharArray();

        int[] pi=new int[pattern.length];

        int j=0;
        for(int i=1;i<pattern.length;i++){
            while(j>0&&pattern[i]!=pattern[j]) j=pi[j-1];
            if(pattern[i]==pattern[j]) pi[i]=++j;
        }

        int answer=0;
        j=0;
        for(int i=0;i<src.length;i++){
            while(j>0&&src[i]!=pattern[j]) j=pi[j-1];
            if(src[i]==pattern[j]){
                if(j==pattern.length-1){
                    ++answer;
                    j=0;
                }else ++j;
            }
        }
        System.out.println(answer);
    }
}

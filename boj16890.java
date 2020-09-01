import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        char[] koo=br.readLine().toCharArray();
        char[] cub=br.readLine().toCharArray();

        Arrays.sort(koo);
        Arrays.sort(cub);
        int n=koo.length;

        char[] koos=new char[n/2+n%2];
        char[] cubs=new char[n/2];

        for(int i=0;i<koos.length;i++) koos[i]=koo[i];
        for(int i=0;i<cubs.length;i++) cubs[i]=cub[cub.length-1-i];

        char[] answer=new char[n];
        int as=0,ae=n-1;
        int ks=0,ke=koos.length-1;
        int cs=0,ce=cubs.length-1;
        for(int i=0;i<n;i++){
            if(i%2==0){//koosaga turn
                if(isRange(ks,ke,cs,ce)&&koos[ks]<cubs[cs]) answer[as++]=koos[ks++];
                else answer[ae--]=koos[ke--];
            }else{//cubelover turn
                if(isRange(ks,ke,cs,ce)&&koos[ks]<cubs[cs]) answer[as++]=cubs[cs++];
                else answer[ae--]=cubs[ce--];
            }
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<n;i++) sb.append(answer[i]);
        System.out.println(sb.toString());
    }
    static boolean isRange(int a,int b,int c,int d){
        return a<=b&&c<=d;
    }
}
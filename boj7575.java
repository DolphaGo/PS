import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr;
    static int n,k;
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());
        arr=new int[n][];
        for(int i=0;i<n;i++){
            int m=Integer.parseInt(br.readLine());
            arr[i]=new int[m];
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++) arr[i][j]=Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<=arr[0].length-k;i++){
            if(kmp(i)){
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }
    static boolean kmp(int st){
        int[] pattern=new int[k];
        int[] reversePattern=new int[k];
        for(int i=st;i<st+k;i++){ // i-st = { 0~ k-1}
            pattern[i-st]=arr[0][i];
            reversePattern[k-1-(i-st)]=pattern[i-st];
        }
        int[] pi=makePi(pattern);
        int[] rpi=makePi(reversePattern);

        for(int i=1;i<n;i++){
            boolean result=kmpTest(i,pattern,pi); //정방향 검사
            if(!result){
                result=kmpTest(i,reversePattern,rpi); //역방향 검사
                if(!result) return false; //둘 다 없다면 false;
            }
        }
        return true;
    }
    static int[] makePi(int[] pattern){
        int[] pi=new int[k];
        for(int i=1,j=0;i<k;i++){
            while(j>0&&pattern[i]!=pattern[j]) j=pi[j-1];
            if(pattern[i]==pattern[j]) pi[i]=++j;
        }
        return pi;
    }

    static boolean kmpTest(int i,int[] pattern,int[] pi){
        int[] src=arr[i];
        for(int idx=0,j=0;idx<src.length;idx++){
            while(j>0&&src[idx]!=pattern[j]) j=pi[j-1];
            if(src[idx]==pattern[j]){
                if(j==k-1) return true;
                else ++j;
            }
        }
        return false;
    }
}
import java.util.*;

public class Main3 {
    static int min,minVal;
    public static void main(String[] args){
        int n=9;
        min=Integer.MAX_VALUE;
        go(n,0);

        int[] answer=new int[2];
        answer[0]=min;
        answer[1]=minVal;

        System.out.println(Arrays.toString(answer));
    }
    static void go(int val,int use){
        int len=String.valueOf(val).length();
        if(len==1){
            if(min>use){
                min=use;
                minVal=val;
            }
            return;
        }
        for(int i=1;i<len;i++){
            int mod=pow10(i);
            int prev=val/mod;
            int next=val%mod;

            //"0" 검사
            int prevLen =String.valueOf(prev).length();
            int nextLen=String.valueOf(next).length();
            if(prevLen +nextLen!=len) continue;

            go(prev+next,use+1);
        }
    }
    static int pow10(int v){
        return v==0?1:10*pow10(v-1);
    }
}
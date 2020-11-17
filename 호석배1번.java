import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class 호석배1번 {
    static long max,min;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n=Long.parseLong(br.readLine());

        max=Integer.MIN_VALUE;
        min=Integer.MAX_VALUE;
        go(n,getOddNum(n));

        System.out.println(min+" "+max);
    }
    static void go(long num,int count){
        String s=String.valueOf(num);
        if(s.length()==1){
            max=Math.max(count,max);
            min=Math.min(count,min);
            return;
        }else if(s.length()==2){
            long next=(s.charAt(0)-'0')+(s.charAt(1)-'0');
             go(next,count+getOddNum(next));
        }else{
            List<Long> nums=getPossibleNumber(num);
            for(long next:nums){
                go(next,count+getOddNum(next));
            }
        }
    }

    private static List<Long> getPossibleNumber(long num) {
        String s=String.valueOf(num);
        int len=s.length();
        int[] check=new int[len];
        List<Long> list=new ArrayList<>();
        parsing(list,check,s,1,1);
        return list;
    }

    private static void parsing(List<Long> list, int[] check, String s, int idx,int mark) {
        if(mark==3){
            long a,b,c;
            StringBuilder sb=new StringBuilder();
            int i=0;
            while(i<s.length()&&check[i]!=1){
                sb.append(s.charAt(i++));
            }
            a=Long.parseLong(sb.toString());
            sb.setLength(0);
            while(i<s.length()&&check[i]!=2){
                sb.append(s.charAt(i++));
            }
            b=Long.parseLong(sb.toString());
            sb.setLength(0);
            while(i<s.length()){
                sb.append(s.charAt(i++));
            }
            c=Long.parseLong(sb.toString());
            list.add(a+b+c);
            return;
        }

        for(int i=idx;i<s.length();i++){
            check[i]=mark;
            parsing(list,check,s,i+1,mark+1);
            check[i]=0;
        }
    }

    static int getOddNum(long num){
        int res=0;
        while(num>0){
            long r=num%10;
            if(r%2==1) ++res;
            num/=10;
        }
        return res;
    }
}
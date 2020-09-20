import java.util.*;

public class Main {
    public static void main(String[] args){
        String s=".a.a.a.c.c.as.asd.as.das.dsa.";
        StringBuilder sb=new StringBuilder();

        // 1
        s=s.toLowerCase();

        // 2
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(check(c)) sb.append(c);
        }
        s=sb.toString();
        sb.setLength(0);

        //3
        for(int i=0;i<s.length();){
            char c=s.charAt(i);
            if(c=='.'){
                while(i<s.length()&&s.charAt(i)=='.') ++i;
                sb.append(c);
            }else{
                sb.append(c);
                i++;
            }
        }

        s=sb.toString();
        sb.setLength(0);

        //4
        if(s.length()>0&&s.charAt(0)=='.') s=s.substring(1,s.length());
        if(s.length()>0&&s.charAt(s.length()-1)=='.') s=s.substring(0,s.length()-1);

        //5
        if(s.length()==0) s="a";
        
        //6
        if(s.length()>=16) s=s.substring(0,15); //0~14 : 15글자

        //6-1
        if(s.charAt(s.length()-1)=='.') s=s.substring(0,s.length()-1);

        //7
        while(s.length()<=2) s=s+s.charAt(s.length()-1);

        System.out.println(s);
    }
    static boolean check(char c){
        if('a'<=c&&c<='z') return true;
        else if('0'<=c&&c<='9') return true;
        else if(c=='-'||c=='_'||c=='.') return true;
        else return false;
    }
}
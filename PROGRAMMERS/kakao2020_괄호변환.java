import java.util.*;

public class Main {
    public static String solution(String p) {
        //1
        if(p.length()==0) return p;

        int cnt=0;
        for(int i=0;i<p.length();i++){
            if(p.charAt(i)=='(') cnt++;
            else cnt--;
        }

        //2
        cnt=p.charAt(0)=='('?1:-1;
        int udx=0;
        for(int i=1;i<p.length();i++){
            if(p.charAt(i)=='(') ++cnt;
            else --cnt;
            //첫 균형잡힌 괄호 문자열
            if(cnt==0){
                udx=i;
                break;
            }
        }

        //3
        Stack<Character> s=new Stack<>();
        boolean flag=true;
        for(int i=0;i<=udx;i++){
            char c=p.charAt(i);
            if(c=='(') s.push(c);
            else{
                if(s.size()>0&&s.peek()=='(') s.pop();
                else{
                    flag=false;
                    break;
                }
            }
        }

        StringBuilder sbu=new StringBuilder();
        for(int i=0;i<=udx;i++) sbu.append(p.charAt(i));
        StringBuilder sbv=new StringBuilder();
        for(int i=udx+1;i<p.length();i++) sbv.append(p.charAt(i));

        //3-1
        if(flag){
            return sbu.toString()+solution(sbv.toString());
        }else{ //4
            StringBuilder sb=new StringBuilder();
            sb.append("("); //4-1
            sb.append(solution(sbv.toString())); //4-2
            sb.append(")"); //4-3

            //4-4
            String newU=sbu.substring(1,sbu.length()-1);
            for(int i=0;i<newU.length();i++){
                char c=newU.charAt(i);
                if(c=='(') sb.append(")");
                else sb.append("(");
            }
            return sb.toString(); //4-5
        }
    }
    public static void main(String[] args){
        String p="()))((()";
        System.out.println(solution(p));
    }
}
import java.util.*;

class Solution4 {
    public static long solution(String s) {
        long answer=0;
        boolean flag=true;
        char pivot=s.charAt(0);
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=pivot){
                flag=false;
                break;
            }
        }
        if(!flag){

        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("babb"));
    }
}
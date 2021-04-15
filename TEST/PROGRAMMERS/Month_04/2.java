import java.util.*;

class Solution {
    public int solution(String s) {
        ArrayList<Character> list= new ArrayList<>();
        for(int i=0;i<s.length();i++){
            list.add(s.charAt(i));
        }
        Stack<Character> stack = new Stack<>();
        int answer=0;
        for(int j=0;j<s.length();j++){
            for(int i=0;i<s.length();i++){
                char c = list.get(i);

                if(stack.size()>0 && correct(stack.peek(),c)){
                    stack.pop();
                }else stack.push(c);
            }
            if(stack.isEmpty()) ++answer;
            list.add(list.get(0));
            list.remove(0);
            stack.clear();
        }

        return answer;
    }
    static boolean correct(char a,char b){
        if(a=='[' && b==']') return true;
        if(a=='{' && b=='}') return true;
        if(a=='(' && b==')') return true;
        return false;
    }
}
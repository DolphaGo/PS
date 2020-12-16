class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack=new Stack<>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c==')'||c==']'||c=='}'){
                if(stack.isEmpty()) return false;
                char top=stack.pop();
                if(!check(top,c)) return false;
            }else stack.push(c);
        }
        if(stack.isEmpty()) return true;
        else return false;

    }
    static boolean check(char open,char close){
        if(open=='('&&close==')') return true;
        if(open=='['&&close==']') return true;
        if(open=='{'&&close=='}') return true;
        return false;
    }
}
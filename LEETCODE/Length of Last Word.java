import java.util.StringTokenizer;

class Solution {
    public int lengthOfLastWord(String s) {
        StringTokenizer st=new StringTokenizer(s);
        int answer=0;
        while(st.hasMoreTokens()){
            answer=st.nextToken().length();
        }
        return answer;
    }
}
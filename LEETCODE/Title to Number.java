class Solution {
    public int titleToNumber(String s) {
        int n=s.length();
        int answer=0;
        for(int i=0;i<n;i++){
            int idx=s.charAt(i)-'A'+1;
            answer+=pow(26,n-1-i)*idx;
        }
        return answer;
    }
    static int pow(int a,int b){
        return b==0? 1: a*pow(a,b-1);
    }
}
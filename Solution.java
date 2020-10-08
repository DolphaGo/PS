class Solution {
    public int solution(int n) {
        int answer = 0;
        StringBuilder sb=new StringBuilder();
        while(n>0){
            sb.append(n%3);
            n/=3;
        }
        String s=sb.toString();
        for(int i=s.length()-1;i>=0;i--){
            int c=s.charAt(i)-'0';
            answer+=c*pow(3,s.length()-1-i);
        }
        return answer;
    }
    static int pow(int a,int b){
        return b==0?1:a*pow(a,b-1);
    }
}
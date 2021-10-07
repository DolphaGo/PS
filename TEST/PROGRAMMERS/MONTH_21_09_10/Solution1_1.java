class Solution {
    public int solution(int n) {
        int answer = 0;
        while(true){
            if(n%(++answer)==1) break;
        }
        return answer;
    }
}

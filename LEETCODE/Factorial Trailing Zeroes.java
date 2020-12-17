class Solution {
    public int trailingZeroes(int n) {
        int i=5;
        int answer=0;
        while(i<=n){
            answer+=n/i;
            i*=5;
        }
        return answer;
    }
}
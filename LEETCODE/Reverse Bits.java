#1 단순 비트
public class Solution {
    public int reverseBits(int n) {
        int answer=0;
        for(int i=0;i<32;i++){
            if((n&(1<<i))!=0) answer+=(1<<(31-i));
        }
        return answer;
    }
}
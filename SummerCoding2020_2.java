import java.util.*;
class Solution {
    public long solution(long n) {
        long answer=0;
        int cnt=0;
        while(n>0){
            long res=n%2;
            answer+=res*Math.pow(3,cnt);
            n/=2;
            cnt++;
        }
        
        return answer;
    }
}
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        int max=0,answer=0;
        for(int num:nums) {
            int count=map.getOrDefault(num,0)+1;
            map.put(num,count);
            if(max<count){
                max=count;
                answer=num;
            }
        }
        return answer;
    }
}
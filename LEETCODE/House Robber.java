class Solution {
    public int rob(int[] nums) {
        int n=nums.length;
        int day1=0,day2=0,day=0;
        for(int i=0;i<n;i++){
            day=Math.max(day1,day2+nums[i]);
            day2=day1;
            day1=day;
        }
        return day;
    }
}
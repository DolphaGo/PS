class Solution {
    public int findDuplicate(int[] nums) {
        if(nums.length == 0) return 0;
        int slow=0, fast=0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while(slow != fast){
            if(slow == nums[slow]) return slow;
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while(slow != fast){
            if(slow == nums[slow]) return slow;
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
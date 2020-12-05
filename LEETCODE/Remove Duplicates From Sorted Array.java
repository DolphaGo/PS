class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length==0) return 0;
        int count=0;
        nums[count++]=nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[count-1]!=nums[i]) {
                nums[count++]=nums[i];
            }
        }
        return count;
    }
}
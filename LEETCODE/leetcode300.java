class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] arr = new int[nums.length];
        int idx=0;
        arr[idx++]=nums[0];        
        for(int i=1;i<nums.length;i++){
            int val=nums[i];
            if(arr[idx-1]<val) arr[idx++]=val;
            else{
                int s=0;
                int e=idx-1;
                while(s<e){
                    int m=(s+e)>>1;
                    if(arr[m]<val) s=m+1;
                    else e=m;
                }
                arr[e]=val;
            }
        }
        return idx;
    }
}

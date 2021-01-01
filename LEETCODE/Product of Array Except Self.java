class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        int[] answer=new int[n];

        int l=1;
        for(int i=0;i<n;i++) {
            answer[i]=l;
            l*=nums[i];
        }

        int r=1;
        for(int i=n-1;i>=0;i--){
            answer[i]*=r;
            r*=nums[i];
        }
        return answer;
    }
}
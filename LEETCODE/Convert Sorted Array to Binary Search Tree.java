class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return go(nums,0,nums.length-1);
    }
    static TreeNode go(int[] nums,int s,int e){
        if(s>e) return null;
        int m=(s+e)>>1;
        TreeNode node=new TreeNode(nums[m]);
        node.left=go(nums,s,m-1);
        node.right=go(nums,m+1,e);
        return node;
    }
}
class Solution {
    public int maxDepth(TreeNode root) {
        return go(root,0);
    }
    static int go(TreeNode node,int depth){
        if(node==null) return depth;
        int max=depth;
        max=Math.max(max,go(node.left,depth+1));
        max=Math.max(max,go(node.right,depth+1));
        return max;
    }
}
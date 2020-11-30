class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        else return isEqual(root.left, root.right);
    }

    public boolean isEqual(TreeNode left, TreeNode right) {
        if (left == null || right == null) return left == right;
        if (left.val != right.val) return false;

        return isEqual(left.left, right.right) && isEqual(left.right, right.left);
    }
}
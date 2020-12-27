class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> answer=new ArrayList<>();
        inorder(root,answer);
        return answer;
    }
    static void inorder(TreeNode node,List<Integer> answer){
        if(node==null) return;
        inorder(node.left,answer);
        answer.add(node.val);
        inorder(node.right,answer);
    }
}
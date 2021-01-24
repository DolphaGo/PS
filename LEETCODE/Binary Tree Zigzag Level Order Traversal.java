import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Deque<TreeNode> q=new ArrayDeque<>();
        q.add(root);
        List<List<Integer>> answer=new ArrayList<>();
        int level=0;
        while(!q.isEmpty()){
            answer.add(getResult(q,level));
            level++;
        }
        return answer;
    }
    static List<Integer> getResult(Deque<TreeNode> q,int level){
        int size=q.size();
        if(level%2==0){
            for(int )

        }else{

        }
    }
}
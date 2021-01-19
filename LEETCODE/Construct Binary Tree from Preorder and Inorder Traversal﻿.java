class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) {
            index.put(inorder[i], i);
        }
        return go(preorder, inorder, 0, n - 1, 0, n - 1, index);
    }

    static TreeNode go(int[] preorder, int[] inorder, int pr_st, int pr_end, int in_st, int in_end, Map<Integer, Integer> index) {
        if (pr_st > pr_end || in_st > in_end) { return null; }
        int root = preorder[pr_st];
        int p = index.get(root); // 루트
        int left = p - in_st; //루트 포함 좌측 트리의 길이
        TreeNode node = new TreeNode(root);
        node.left = go(preorder, inorder, pr_st + 1, pr_st + left, in_st, p - 1, index);
        node.right = go(preorder, inorder, pr_st + left + 1, pr_end, p + 1, in_end, index);
        return node;
    }
}
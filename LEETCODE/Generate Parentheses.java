class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> answer = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        go(answer, sb, 0, 0, n);
        return answer;
    }

    static void go(List<String> answer, StringBuilder sb, int left, int right, int n) {
        if (left == n && right == n) {
            answer.add(sb.toString());
            return;
        }

        if (left < n) {
            sb.append("(");
            go(answer, sb, left + 1, right, n);
            sb.setLength(sb.length() - 1);
        }

        if (right < left) {
            sb.append(")");
            go(answer, sb, left, right + 1, n);
            sb.setLength(sb.length() - 1);
        }
    }
}
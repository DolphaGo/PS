class Solution {
    public String solution(String new_id) {
        // 1단계
        StringBuilder sb = new StringBuilder(new_id.toLowerCase());

        // 2단계
        int i = 0;
        while (i < sb.length()) {
            char c = sb.charAt(i);
            if (isPassCase(c)) {
                i++;
            } else {
                sb.deleteCharAt(i);
            }
        }

        // 3단계
        i = 0;
        while (i < sb.length()) {
            char c = sb.charAt(i);
            if (c == '.') {
                while (i + 1 < sb.length() && sb.charAt(i + 1) == '.') {
                    sb.deleteCharAt(i);
                }
            }
            i++;
        }

        // 4단계
        deleteIfHaveDot(sb, 0);
        deleteIfHaveDot(sb, sb.length() - 1);

        // 5단계
        if (sb.length() == 0) {
            sb.append("a");
        }

        // 6단계
        if (sb.length() >= 16) {
            sb.setLength(15);
            deleteIfHaveDot(sb, sb.length() - 1);
        }

        // 7단계
        if (sb.length() <= 2) {
            while (sb.length() < 3) {
                sb.append(sb.charAt(sb.length()-1));
            }
        }

        return sb.toString();
    }

    private void deleteIfHaveDot(final StringBuilder sb, final int i) {
        if (sb.length() > 0) {
            if (sb.charAt(i) == '.') {
                sb.deleteCharAt(i);
            }
        }
    }

    private boolean isPassCase(final char c) {
        if ('0' <= c && c <= '9') {
            return true;
        }
        if ('a' <= c && c <= 'z') {
            return true;
        }
        if (c == '-' || c == '_' || c == '.') {
            return true;
        }
        return false;
    }
}


class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if('a'<=c&&c<='z' || ('0'<=c&& c<='9')) sb.append(c);
            if('A'<=c&&c<='Z') sb.append((char)(c-'A'+'a'));
        }
        String src=sb.toString();
        return src.equals(sb.reverse().toString());
    }
}
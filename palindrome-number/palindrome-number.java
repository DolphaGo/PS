class Solution {
    public boolean isPalindrome(int x) {
        if(x<0) return false;
        String s = String.valueOf(x);
        int m = s.length()/2;
        for(int i=0;i<m;i++){
            if(s.charAt(i) != s.charAt(s.length()-1-i)) return false;
        }
        return true;
    }
}
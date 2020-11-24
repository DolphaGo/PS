class Solution {
    public int firstUniqChar(String s) {
        int[] alpha=new int[26];
        for(int i=0;i<s.length();i++){
            int idx=s.charAt(i)-'a';
            alpha[idx]++;
        }
        for(int i=0;i<s.length();i++){
            int idx=s.charAt(i)-'a';
            if(alpha[idx]==1) return i;
        }
        return -1;
    }
}
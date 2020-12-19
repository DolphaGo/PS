class Solution {
    public int strStr(String haystack, String needle) {
        int n=needle.length();
        if(n==0) return 0;

        int[] pi=new int[n];
        for(int i=1,j=0;i<n;i++){
            while(j>0&&needle.charAt(i)!=needle.charAt(j)) j=pi[j-1];
            if(needle.charAt(i)==needle.charAt(j)) pi[i]= ++j;
        }

        for(int i=0,j=0;i<haystack.length();i++){
            while(j>0&&haystack.charAt(i)!=needle.charAt(j)) j=pi[j-1];
            if(haystack.charAt(i)==needle.charAt(j)){
                ++j;
                if(j==n) return i+1-n;
            }
        }
        return -1;
    }
}
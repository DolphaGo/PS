class Solution {
    public String countAndSay(int n) {
        if(n==1) return "1";
        return reformat(countAndSay(n-1));
    }

    static String reformat(String s){
        StringBuilder sb=new StringBuilder();
        char c=s.charAt(0);
        int cnt=1;
        for(int i=1;i<s.length();i++){
            if(c!=s.charAt(i)){
                sb.append(cnt).append(c);
                cnt=1;
                c=s.charAt(i);
            }else cnt++;
        }
        sb.append(cnt).append(c);
        return sb.toString();
    }
}
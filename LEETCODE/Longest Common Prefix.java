class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";

        Arrays.sort(strs,new Comparator<String>(){
            public int compare(String o1,String o2){
                return Integer.compare(o1.length(),o2.length());
            }
        });
        StringBuilder sb=new StringBuilder();
        sb.append(strs[0]);
        for(int i=0;i< strs.length; i++){
            String s=strs[i];
            for(int j=0;j<sb.length();j++){
                char c=s.charAt(j);
                if(sb.charAt(j)!=c){
                    sb.setLength(j);
                    break;
                }
            }
        }
        return sb.toString();
    }
}
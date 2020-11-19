class Solution {
    public int[] solution(String s) {
        int count=0;
        int remove=0;
        StringBuilder sb=new StringBuilder();
        while(s.length()!=1){
            ++count;
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)=='0') ++remove;
                else sb.append(1);
            }

            s=Integer.toBinaryString(sb.length());
            sb.setLength(0);
        }

        return new int[]{count,remove};
    }
}
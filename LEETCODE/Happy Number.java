class Solution {
    public boolean isHappy(int n) {
        Map<Integer,Boolean> map=new HashMap<>();
        while(true){
            String s=String.valueOf(n);
            int sum=0;
            for(int i=0;i<s.length();i++){
                int val=s.charAt(i)-'0';
                sum+=val*val;
            }
            if(sum==1) return true;
            if(map.containsKey(sum)) break;

            map.put(sum,true);
            n=sum;
        }
        return false;
    }
}
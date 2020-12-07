class Solution {
    public boolean isPowerOfThree(int n) {
        Map<Integer,Boolean> map=new HashMap<>();
        for(long i = 1; i<=(1L <<31)-1; i*=3){
            map.put((int)i,true);
        }
        return map.containsKey(n);
    }
}//20ms
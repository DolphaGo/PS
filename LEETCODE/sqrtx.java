class Solution {
    public int mySqrt(int x) {
        long s=0;
        long e=(1<<31)-1;
        while(s<e){
            long m=(s+e)>>1;
            if(m*m<=x) s=m+1;
            else e=m;
        }
        return (int)(e-1);
    }
}
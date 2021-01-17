class Solution {
    public int getSum(int a, int b) {
        int c = 1;
        while(c != 0) {
            c = a & b;
            a = a ^ b;
            b = c << 1;
        }
        return a;
    }
}
public class Solution {
    public int reverseBits(int n) {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<32;i++){
            if((n&(1<<i))!=0) sb.append(1);
            else sb.append(0);
        }
        return Integer.parseUnsignedInt(sb.toString(),2);
    }
}
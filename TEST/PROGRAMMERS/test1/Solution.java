class Solution {
    public int solution(int[] sortedArray, int findValue) {
        int s=0;
        int e=sortedArray.length;
        while(s<e){
            int mid=(s+e)>>1;
            int value = sortedArray[mid];
            if(value<findValue) s=mid+1;
            else e=mid;
        }
        if(e==sortedArray.length || sortedArray[e] != findValue) return -1;
        return e;
    }
}
